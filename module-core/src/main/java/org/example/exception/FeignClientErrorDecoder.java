package org.example.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        // 예시: HTTP 404 에러 발생 시, 특정 예외 던지기
        if (response.status() == 404) {
            return new CustomException(CustomErrorCode.FAILED_FEIGN_NOT_FOUND);
        }
        return new Default().decode(methodKey, response);
    }
}
