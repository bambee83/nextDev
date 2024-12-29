package org.example.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Deprecated
@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new FiledLogTrace();
    }
}
