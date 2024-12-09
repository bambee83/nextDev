package org.example.nexthw.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 전역 예외 처리
@Slf4j @RestControllerAdvice
public class CustomExceptionHandler {

    // CustomException 핸들러
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseMessage> handleCustomException(CustomException ex) {
        log.warn("Custom exception occurred: {}", ex.getMessage());
        return ResponseEntity.status(ex.getErrorCode())
                .body(ResponseMessage.builder()
                        .message(ex.getMessage())
                        .statusCode(ex.getErrorCode())
                        .data(null)
                        .build());
    }

/*    // 최상위 Exception 핸들러 (500 처리)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> handleException(Exception ex) {
        log.error("Unexpected exception occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(CustomErrorCode.INTERNAL_SERVER_ERROR.getStatusCode())
                .body(ResponseMessage.builder()
                        .message(CustomErrorCode.INTERNAL_SERVER_ERROR.getMessage())
                        .statusCode(CustomErrorCode.INTERNAL_SERVER_ERROR.getStatusCode())
                        .data(null)
                        .build());
    }*/


}
