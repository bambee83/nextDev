package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.Decoder;

import java.io.IOException;
import java.lang.reflect.Type;

public class FeignClientDecoder implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws IOException {
        // 응답 본문을 객체로 변환하는 로직
        String body = Util.toString(response.body().asReader());
        return new ObjectMapper().readValue(body, (Class<?>) type);
    }
}