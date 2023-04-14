package com.dku.mentoring.mission.exception;

import com.dku.mentoring.exception.ApplicationException;
import com.dku.mentoring.exception.ErrorCode;

public class MissionNotFoundException extends ApplicationException {
    public MissionNotFoundException() {
        super(ErrorCode.MISSION_NOT_FOUND);
    }

    public MissionNotFoundException(String message) {
        super(message, ErrorCode.MISSION_NOT_FOUND);
    }
}
