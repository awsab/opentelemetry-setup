/**
 * Author   : Prabakaran Ramu
 * User     : ramup
 * Date     : 08/07/2025
 * Usage    :
 * Since    : Version 1.0
 */
package org.me.practise.otel.config;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.autoconfigure.spi.AutoConfigurationCustomizerProvider;
import io.opentelemetry.sdk.autoconfigure.spi.ConfigProperties;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SpanExporter;
import io.opentelemetry.sdk.trace.samplers.Sampler;
import io.opentelemetry.semconv.UrlAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Map;

@Configuration
public class OpenTelemetryConfig {

    @Bean
    public OpenTelemetry openTelemetry() {
        return OpenTelemetrySdk.builder()
                .setTracerProvider(SdkTracerProvider.builder().build())
                .build();

    }

    @Bean
    public Tracer tracer(OpenTelemetry openTelemetry) {
        return openTelemetry.getTracer("otel-victoria-spring-boot", "1.0.0");
    }

    /*@Bean
    public AutoConfigurationCustomizerProvider otelCustomizer() {
        return p ->
                p.addSamplerCustomizer(this::configureSampler)
                 .addSpanExporterCustomizer(this::configureSpanExporter);
    }

    *//** suppress spans for actuator endpoints *//*
    private RuleBasedRoutingSampler configureSampler(Sampler fallback, ConfigProperties config) {
        return RuleBasedRoutingSampler.builder( SpanKind.SERVER, fallback)
                                      .drop( UrlAttributes.URL_PATH, "^/actuator")
                                      .build();
    }

    *//**
     * Configuration for the OTLP exporter. This configuration will replace the default OTLP exporter,
     * and will add a custom header to the requests.
     *//*
    private SpanExporter configureSpanExporter(SpanExporter exporter, ConfigProperties config) {
        if (exporter instanceof OtlpHttpSpanExporter) {
            return ((OtlpHttpSpanExporter) exporter).toBuilder().setHeaders(this::headers).build();
        }
        return exporter;
    }

    private Map<String, String> headers() {
        return Collections.singletonMap("Authorization", "Bearer " + refreshToken());
    }

    private String refreshToken() {
        // e.g. read the token from a kubernetes secret
        return "token";
    }*/
}
