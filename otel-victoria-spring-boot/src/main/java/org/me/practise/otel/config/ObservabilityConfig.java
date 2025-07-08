package org.me.practise.otel.config;


import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Tracer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

//@Configuration
public class ObservabilityConfig {

    @Bean
    @ConditionalOnMissingBean
    public Tracer tracer() {
        return GlobalOpenTelemetry.getTracer("otel-victoria-spring-boot", "1.0.0");
    }

    /*@Bean
    @ConditionalOnMissingBean
    public Meter meter() {
        return GlobalOpenTelemetry.getMeter("otel-victoria-spring-boot", "1.0.0");
    }*/


}

