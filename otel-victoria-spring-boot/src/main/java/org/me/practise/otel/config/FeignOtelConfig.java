package org.me.practise.otel.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.propagation.TextMapSetter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignOtelConfig {

    private static final TextMapSetter<RequestTemplate> setter = (template, key, value) -> template.header ( key, value );

    @Bean
    public RequestInterceptor otelFeignRequestInterceptor() {
        return template -> {
            Context context = Context.current ();
            GlobalOpenTelemetry.getPropagators ()
                               .getTextMapPropagator ()
                               .inject ( context, template, setter );
        };
    }


    @Bean
    public RequestInterceptor debugHeadersInterceptor() {
        return template -> {
            Context context = Context.current ();
            GlobalOpenTelemetry.getPropagators ()
                               .getTextMapPropagator ()
                               .inject ( context, template, (t,k,v) -> {
                                   assert t != null;
                                   t.header ( k, v );
                               } );
            System.out.println ( "Outgoing Feign headers: " + template.headers () );
        };
    }

}