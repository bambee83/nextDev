package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Type;

@Slf4j
public class FeignClientDecoder implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws IOException {
        // 응답 본문을 객체로 변환하는 로직
        String body = Util.toString(response.body().asReader());  // 응답 본문을 문자열로 읽기
        log.debug("Response Body: {}", body);  // 응답 본문 로그
        return new ObjectMapper().readValue(body, (Class<?>) type); // JSON 문자열을 DTO로 변환
    }
}