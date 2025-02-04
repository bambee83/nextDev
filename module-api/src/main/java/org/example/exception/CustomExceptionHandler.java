package org.example.exception;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice // CustomException 발생 시, 프런트로 보낼 ErrorDTO 를 생성하고, ErrorDTO 를 전달하는 handler
public class CustomExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ResponseMessage> handleCustomException(CustomException e) {
        // 에러에 대한 후처리
        log.error("[handleCustomException] {} : {}", e.getCustomErrorCode().name(), e.getCustomErrorCode().getMessage());
        return ResponseMessage.error(e);
    }

    // FeignException 처리
    @ExceptionHandler(value = FeignException.class)
    public ResponseEntity<ResponseMessage> handleFeignException(FeignException e) {
        log.error("[handleFeignException] Status: {}, Message: {}", e.status(), e.getMessage());
        HttpStatus status = HttpStatus.valueOf(e.status());
        String message = e.getMessage();
        return ResponseEntity
                .status(status)
                .body(new ResponseMessage<>(
                        status,
                        message,
                        null
                ));
    }

    // HttpRequestMethodNotSupportedException 처리
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseMessage> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        log.error("[handleHttpRequestMethodNotSupported] {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseMessage<>(
                        HttpStatus.BAD_REQUEST,
                        CustomErrorCode.NOT_SUPPORTED_HTTP_METHOD.getMessage(),
                        null
                ));
    }

    // MethodArgumentNotValidException 처리
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessage> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        log.error("[handleMethodArgumentNotValid] {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseMessage<>(
                        HttpStatus.BAD_REQUEST,
                        CustomErrorCode.NOT_VALID_METHOD_ARGUMENT.getMessage(),
                        null
                ));
    }

    // 전역 예외 처리 (기타)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> handleGeneralException(Exception e) {
        log.error("[handleGeneralException] {}", e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseMessage<>(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        CustomErrorCode.INTERNAL_SERVER_ERROR.getMessage(),
                        null
                ));
    }
}
