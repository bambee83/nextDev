package org.example.utils;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.example.exception.CustomErrorCode;
import org.example.exception.CustomException;

@Slf4j
public class FeignClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        log.error("Error occurred. Status code: {}, Method: {}, Body: {}", response.status(), methodKey, response.body());

        // HTTP 상태 코드에 따른 예외 처리
        switch (response.status()) {
            case 400:
                // 클라이언트 오류 (잘못된 요청 등)
                return new CustomException(CustomErrorCode.FEIGN_BAD_REQUEST);
            case 404:
                // 리소스를 찾을 수 없는 오류
                return new CustomException(CustomErrorCode.FEIGN_NOT_FOUND);
            default:
                // 서버 오류
                return new CustomException(CustomErrorCode.FEIGN_SERVER_ERROR);
        }
    }
}