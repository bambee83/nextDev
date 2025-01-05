package org.example.utils;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        // 요청 헤더에 인증 토큰 추가
        template.header("Authorization", "A");
    }
}