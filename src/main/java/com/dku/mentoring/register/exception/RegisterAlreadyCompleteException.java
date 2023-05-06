package com.dku.mentoring.register.exception;

import com.dku.mentoring.exception.ApplicationException;
import com.dku.mentoring.exception.ErrorCode;

public class RegisterAlreadyCompleteException extends ApplicationException {
    public RegisterAlreadyCompleteException(ErrorCode errorCode) {
        super(ErrorCode.REGISTER_ALREADY_COMPLETE);
    }

    public RegisterAlreadyCompleteException(String message) {
        super(message, ErrorCode.REGISTER_ALREADY_COMPLETE);
    }
}
