package org.me.practise.otel.config;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
@Slf4j
public class TracingInterceptor implements HandlerInterceptor {

    private final Tracer tracer;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // Extract trace and span IDs from headers if present
        String traceId = request.getHeader("X-Trace-Id");
        String spanId = request.getHeader("X-Span-Id");

        if (traceId != null && spanId != null) {
            MDC.put("traceId", traceId);
            MDC.put("spanId", spanId);
            log.debug("Extracted trace ID: {} and span ID: {} from headers", traceId, spanId);
        } else {
            // Get current span from OpenTelemetry
            Span currentSpan = Span.current();
            if (currentSpan.getSpanContext().isValid()) {
                String currentTraceId = currentSpan.getSpanContext().getTraceId();
                String currentSpanId = currentSpan.getSpanContext().getSpanId();

                MDC.put("traceId", currentTraceId);
                MDC.put("spanId", currentSpanId);
                log.debug("Using current trace ID: {} and span ID: {}", currentTraceId, currentSpanId);
            }
        }

        // Create a custom span for this request
        Span span = tracer.spanBuilder("http-request")
                          .setSpanKind(SpanKind.SERVER)
                          .setAttribute("http.method", request.getMethod())
                          .setAttribute("http.url", request.getRequestURL().toString())
                          .setAttribute("http.route", request.getServletPath())
                          .startSpan();

        // Store span in request for cleanup
        request.setAttribute("otel.span", span);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // Get the span from request and finish it
        Span span = (Span) request.getAttribute("otel.span");
        if (span != null) {
            span.setAttribute("http.status_code", response.getStatus());

            if (ex != null) {
                span.recordException(ex);
                span.setStatus(StatusCode.ERROR, ex.getMessage());
            } else {
                span.setStatus(StatusCode.OK);
            }

            span.end();
        }

        // Clear MDC
        MDC.clear();
    }
}