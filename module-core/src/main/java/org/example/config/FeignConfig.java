package org.example.config;

import feign.RequestInterceptor;
import feign.Retryer;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.FeignClientDecoder;
import org.example.utils.FeignClientErrorDecoder;
import org.example.utils.FeignClientInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration @Slf4j
@EnableFeignClients("org.example") // FeignClient 활성화
public class FeignConfig {

    @Bean
    public FeignClientErrorDecoder feignClientErrorDecoder() {
        return new FeignClientErrorDecoder();   // 커스텀 ErrorDecoder
    }

    @Bean
    public FeignClientDecoder feignClientDecoder() {
        return new FeignClientDecoder();    // 커스텀 Decoder
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignClientInterceptor();  // 커스텀 RequestInterceptor
    }

    @Bean
    public Retryer retryer() {
        // 재시도 최대 3번, 각 재시도 간 1초 대기
        return new Retryer.Default(100, TimeUnit.SECONDS.toMillis(1), 3);
    }
}
