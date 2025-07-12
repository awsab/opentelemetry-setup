package org.me.practise.parentproject.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FeignTracingConfig {

    /*@Bean
    public RequestInterceptor tracingRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                String traceId = MDC.get("traceId");
                String spanId = MDC.get("spanId");
                String businessTraceId = MDC.get("businessTraceId");
                if (traceId != null) {
                    template.header("traceId", traceId);
                }
                if (spanId != null) {
                    template.header("spanId", spanId);
                }
                if (businessTraceId != null) {
                    template.header("businessTraceId", businessTraceId);
                }

                System.out.println("Feign traceId: " + traceId + ", spanId: " + spanId +
                        ", businessTraceId: " + businessTraceId);
            }

        };
    }*/
}