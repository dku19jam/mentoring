package com.dku.mentoring.register.exception;

import com.dku.mentoring.exception.ApplicationException;
import com.dku.mentoring.exception.ErrorCode;

public class NoRightToAccessException extends ApplicationException {
    public NoRightToAccessException() {
        super(ErrorCode.NO_PERMISSION);
    }

    public NoRightToAccessException(String message) {
        super(message, ErrorCode.NO_PERMISSION);
    }
}
