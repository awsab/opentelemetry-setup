package com.me.practise.otel.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public abstract class TraceLoggingFilter {
    /*private static final Logger logger = LoggerFactory.getLogger(TraceLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        if (request instanceof HttpServletRequest httpRequest) {
            String traceId = httpRequest.getHeader("traceId");
            String spanId = httpRequest.getHeader("spanId");
            String businessTraceId = httpRequest.getHeader("businessTraceId");

            if ((traceId == null || spanId == null)) {
                String traceParent = httpRequest.getHeader("traceparent");
                if (traceParent != null) {
                    String[] parts = traceParent.split("-");
                    if (parts.length == 4) {
                        traceId = parts[1];
                        spanId = parts[2];
                    }
                }
            }

            MDC.put("traceId", traceId);
            MDC.put("spanId", spanId);
            MDC.put("businessTraceId", businessTraceId);

            logger.info("Incoming traceId: {}, spanId: {}, businessTraceId: {}", traceId, spanId, businessTraceId);
        }
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            logger.error("Error during request processing", e);
        }
    }*/
}
