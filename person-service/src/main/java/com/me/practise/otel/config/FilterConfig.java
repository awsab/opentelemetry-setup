package com.me.practise.otel.config;

import com.me.practise.otel.filter.TraceLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<TraceLoggingFilter> traceLoggingFilter() {
        FilterRegistrationBean<TraceLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TraceLoggingFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
