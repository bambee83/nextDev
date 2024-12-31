package org.example.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@RequiredArgsConstructor
public class ResponseMessage<T> {
    private final HttpStatus status;
    private final String message;
    private final T data;

    public static ResponseEntity<ResponseMessage> error(CustomException e) {
        return ResponseEntity
                .status(e.getCustomErrorCode().getHttpStatus())
                .body(ResponseMessage.builder()
                        .status(e.getCustomErrorCode().getHttpStatus())
                        .message(e.getCustomErrorCode().getMessage())
                        .build());
    }

    public static <T> ResponseEntity<ResponseMessage> success(HttpStatus status, String message, T data) {
        return ResponseEntity
                .status(status)
                .body(ResponseMessage.builder()
                        .status(status)
                        .message(message)
                        .data(data)
                        .build());
    }
}
