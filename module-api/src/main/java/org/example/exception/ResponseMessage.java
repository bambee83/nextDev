package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public record ResponseMessage<T>(HttpStatus status, String message, T data) {
    public static ResponseEntity<ResponseMessage> error(CustomException e) {
        return ResponseEntity
                .status(e.getCustomErrorCode().getHttpStatus())
                .body(new ResponseMessage<>(
                        e.getCustomErrorCode().getHttpStatus(),
                        e.getCustomErrorCode().getMessage(),
                        null
                ));
    }

    public static <T> ResponseEntity<ResponseMessage> success(HttpStatus status, String message, T data) {
        return ResponseEntity
                .status(status)
                .body(new ResponseMessage<>(
                        status,
                        message,
                        data
                ));
    }
}
