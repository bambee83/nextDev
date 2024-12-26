package org.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 전역 예외 처리
@Slf4j
@RestControllerAdvice // 각 컨트롤러에 advice 역할을 하는 @ , bean 등록 포함
public class CustomExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ResponseMessage> handleCustomException(CustomException e) {
        // 에러에 대한 후처리
        log.error("[handleCustomException] {} : {}",e.getCustomErrorCode().name(), e.getCustomErrorCode().getMessage());
        return ResponseMessage.error(e);
    }

    // HttpRequestMethodNotSupportedException 처리
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseMessage> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        log.error("[handleHttpRequestMethodNotSupported] {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseMessage.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message(CustomErrorCode.NOT_SUPPORTED_HTTP_METHOD.getMessage())
                        .build());
    }

    // MethodArgumentNotValidException 처리
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessage> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        log.error("[handleMethodArgumentNotValid] {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseMessage.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .message(CustomErrorCode.NOT_VALID_METHOD_ARGUMENT.getMessage())
                        .build());
    }

    // 전역 예외 처리 (기타)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> handleGeneralException(Exception e) {
        log.error("[handleGeneralException] {}", e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseMessage.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message(CustomErrorCode.INTERNAL_SERVER_ERROR.getMessage())
                        .build());
    }
}
