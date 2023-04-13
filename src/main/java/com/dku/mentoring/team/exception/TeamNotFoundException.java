package com.dku.mentoring.team.exception;

import com.dku.mentoring.exception.ApplicationException;
import com.dku.mentoring.exception.ErrorCode;

public class TeamNotFoundException extends ApplicationException {
    public TeamNotFoundException() {
        super(ErrorCode.TEAM_NOT_FOUND);
    }

    public TeamNotFoundException(String message) {
        super(message, ErrorCode.TEAM_NOT_FOUND);
    }
}
