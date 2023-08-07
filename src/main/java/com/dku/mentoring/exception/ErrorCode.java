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
    TOKEN_NOT_VALIDATED(400,"T001","토큰이 유효하지 않습니다"),
    MISSION_NOT_FOUND(400,"M001","미션이 조회되지 않습니다"),
    REGISTER_NOT_FOUND(400,"R001","해당 인증글이 조회되지 않습니다."),
    USER_NOT_FOUND(400,"U001","해당 유저가 조회되지 않습니다."),
    TEAM_NOT_FOUND(400,"T002","해당 팀이 조회되지 않습니다"),
    REGISTER_FILE_NOT_FOUND(400,"F001","파일이 조회되지 않습니다"),
    MISSION_ALREADY_IN_PROGRESS(400,"M002","이미 등록된 미션입니다"),
    REGISTER_ALREADY_COMPLETE(400,"R002","이미 인증이 완료된 글입니다"),
    REGISTER_NOT_COMPLETE(400, "R003", "인증이 완료되지 않은 글입니다"),
    NO_PERMISSION(403,"P001", "해당 글에 대한 수정 권한이 없습니다")
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