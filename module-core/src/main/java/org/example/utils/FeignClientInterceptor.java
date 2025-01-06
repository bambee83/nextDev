package org.example.utils;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        // 요청 헤더 추가 등록
        log.info("Start FeignClientInterceptor");
        // api-version 헤더를 추가
        template.header("api-version", "3.0");
        // 헤더 값 출력
        log.info("Feign Client Request Headers: {}", template.headers());
    }
}