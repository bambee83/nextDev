package org.example.utils;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("org.example.utils") // FeignClient 활성화
public class FeignConfig {
}
