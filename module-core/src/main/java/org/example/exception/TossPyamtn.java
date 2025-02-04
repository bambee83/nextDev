package org.example.exception;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum TossPyamtn {
    NOT_FOUND(CustomErrorCode.NOT_FOUND),
    BAD_REQUEST(CustomErrorCode.BAD_REQUEST),
    SERVER_ERROR(CustomErrorCode.INTERNAL_SERVER_ERROR);

    private final CustomErrorCode customErrorCode;

    // CustomException 반환
    public CustomException toException() {
        return new CustomException(this.customErrorCode);
    }
}
