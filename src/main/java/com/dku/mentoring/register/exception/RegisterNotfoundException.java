package com.dku.mentoring.register.exception;

import com.dku.mentoring.exception.ApplicationException;
import com.dku.mentoring.exception.ErrorCode;

public class RegisterNotfoundException extends ApplicationException {

    public RegisterNotfoundException() {
        super(ErrorCode.REGISTER_NOT_FOUND);
    }

    public RegisterNotfoundException(String message) {
        super(message, ErrorCode.REGISTER_NOT_FOUND);
    }
}
