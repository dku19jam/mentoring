package com.dku.mentoring.mission.exception;

import com.dku.mentoring.exception.ApplicationException;
import com.dku.mentoring.exception.ErrorCode;

public class MissionAlreadyInProgress extends ApplicationException {
    public MissionAlreadyInProgress() {
        super(ErrorCode.MISSION_ALREADY_IN_PROGRESS);
    }

    public MissionAlreadyInProgress(String message) {
        super(message, ErrorCode.MISSION_ALREADY_IN_PROGRESS);
    }
}
