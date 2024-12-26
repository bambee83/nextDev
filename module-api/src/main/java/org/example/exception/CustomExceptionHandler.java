package org.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 전역 예외 처리
@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ResponseMessage> handleCustomException(CustomException e) {
        // 에러에 대한 후처리
        log.error("[handleCustomException] {} : {}",e.getErrorCode().name(), e.getErrorCode().getMessage());
        return ResponseMessage.error(e);
    }

}
