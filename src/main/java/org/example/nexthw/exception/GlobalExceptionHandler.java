package org.example.nexthw.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 전역 예외 처리
@Slf4j @RestControllerAdvice
public class GlobalExceptionHandler {

    // CustomException 핸들러
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseMessage> handleCustomException(CustomException ex) {
        log.warn("Custom exception occurred: {}", ex.getMessage());
        return buildErrorResponse(ex.getMessage(), ex.getStatusCode());
    }

    // 최상위 Exception 핸들러
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> handleException(Exception ex) {
        log.error("Unexpected exception: {}", ex.getMessage(), ex);
        return buildErrorResponse(CustomErrorCode.INTERNAL_SERVER_ERROR.getMessage(),
                CustomErrorCode.INTERNAL_SERVER_ERROR.getStatusCode());
    }


    private ResponseEntity<ResponseMessage> buildErrorResponse(String message, int statusCode) {
        return ResponseEntity.status(statusCode)
                .body(ResponseMessage.builder()
                        .message(message)
                        .statusCode(statusCode)
                        .data(null)
                        .build());
    }

}
