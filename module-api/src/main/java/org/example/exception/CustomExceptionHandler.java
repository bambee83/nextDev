package org.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 전역 예외 처리
@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    // CustomException 핸들러
    @ExceptionHandler(value = {CustomException.class})
    protected ResponseMessage handleCustomException(CustomException e) {
        log.warn("Custom exception : {}", e.getMessage());
        return ResponseMessage.error(e.getErrorCode());
    }

    // 내부 서버 에러 500
    @ExceptionHandler(value = {Exception.class})
    protected ResponseMessage handleException(Exception e) {
        log.warn("Exception : {}", e.getMessage());
        return ResponseMessage.error(CustomErrorCode.INTERNAL_SERVER_ERROR);
    }

}
