package org.example.nexthw.exception;

import lombok.Getter;

@Getter
public enum CustomErrorCode {
//    IllegalArgumentException(400, "해당 게시물을 찾을 수 없습니다."),
    BAD_REQUEST(400, "잘못된 요청입니다."),
    NOT_FOUND(404, "리소스를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(500, "서버 오류가 발생했습니다.");

    private final int statusCode;
    private final String message;

    CustomErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
