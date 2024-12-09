package org.example.nexthw.exception;

import lombok.Getter;

@Getter
public enum CustomErrorCode {
    IllegalArgumentException(404, "해당 게시물을 찾을 수 없습니다."),
    BAD_REQUEST(400, "잘못된 요청입니다.");

    private final int errorCode;
    private final String message;

    CustomErrorCode(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
