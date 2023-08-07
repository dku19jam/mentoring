package com.dku.mentoring.register.exception;

import com.dku.mentoring.exception.ApplicationException;
import com.dku.mentoring.exception.ErrorCode;

public class RegisterNotCompleteException extends ApplicationException {
    public RegisterNotCompleteException(ErrorCode errorCode) {
        super(errorCode);
    }

    public RegisterNotCompleteException(String message) {
        super(message, ErrorCode.REGISTER_NOT_COMPLETE);
    }
}
