package org.example.nexthw.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ResponseMessage {
    private final String message;
    private final int statusCode;
    private final Object data;

    // 성공 응답
    public static ResponseMessage success(String message, Object data) {
        return ResponseMessage.builder()
                .message(message)
                .statusCode(200)
                .data(data)
                .build();
    }

    // 에러 응답
//    public static ResponseMessage error(CustomErrorCode errorCode) {
//        return ResponseMessage.builder()
//                .message(errorCode.getMessage())
//                .statusCode(errorCode.getErrorCode())
//                .data(null)
//                .build();
//    }


}
