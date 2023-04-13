package com.dku.mentoring.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ErrorCode {

    /**
     * 에러코드
     */
    ALREADY_EXIST(400, "G001", "이미 존재합니다"),
    TOKEN_NOT_VALIDATED(400,"T001","토큰이 유효하지 않습니다")
    ;


    private final int httpStatus;
    private final String code;
    private final String message;

    ErrorCode(final int httpStatus, final String code, final String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}