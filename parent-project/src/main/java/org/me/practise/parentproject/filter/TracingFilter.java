package org.me.practise.parentproject.filter;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

//@Component
public class TracingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            Span currentSpan = Span.current();
            SpanContext context = currentSpan.getSpanContext();
            if (context.isValid()) {
                MDC.put("traceId", context.getTraceId());
                MDC.put("spanId", context.getSpanId());
            }
            // Extract or generate businessId
            String businessTraceId = request.getHeader("businessTraceId");
            if (businessTraceId == null || businessTraceId.isEmpty()) {
                businessTraceId = UUID.randomUUID().toString();
            }
            MDC.put("businessTraceId", businessTraceId);
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove("traceId");
            MDC.remove("spanId");
            MDC.remove("businessTraceId");
        }
    }
}