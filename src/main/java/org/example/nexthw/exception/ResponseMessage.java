package org.example.nexthw.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ResponseMessage {
    private final String message;
    private final int statusCode;
    private final Object data;

    // 성공 응답
    public static ResponseMessage success(HttpStatus httpStatus, String message, Object data) {
        return ResponseMessage.builder()
                .message(message)
                .statusCode(httpStatus.value())
                .data(data)
                .build();
    }

    // 에러 응답
    public static ResponseMessage error(CustomErrorCode errorCode) {
        return ResponseMessage.builder()
                .message(errorCode.getMessage())
                .statusCode(errorCode.getHttpStatus().value())
                .data(null)
                .build();
    }


}
