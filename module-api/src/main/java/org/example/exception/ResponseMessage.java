package org.example.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@RequiredArgsConstructor
public class ResponseMessage {
    private final HttpStatus status;
    private final String message;
    private final Object data;

    public ResponseMessage(CustomErrorCode customErrorCode) {
        this.status = customErrorCode.getHttpStatus();
        this.message = customErrorCode.getMessage();
        this.data = null;
    }

    public static ResponseEntity<ResponseMessage> error(CustomException e) {
        return ResponseEntity
                .status(e.getCustomErrorCode().getHttpStatus())
                .body(ResponseMessage.builder()
                        .status(e.getCustomErrorCode().getHttpStatus())
                        .message(e.getCustomErrorCode().getMessage())
                        .build());
    }

    public static ResponseMessage success(HttpStatus status, String message, Object data) {
        return ResponseMessage.builder()
                .status(status)
                .message(message)
                .data(data)
                .build();
    }
}
