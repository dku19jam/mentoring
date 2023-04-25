package com.dku.mentoring.register.exception;

import com.dku.mentoring.exception.ApplicationException;
import com.dku.mentoring.exception.ErrorCode;

public class RegisterFileNotfoundException extends ApplicationException {
    public RegisterFileNotfoundException() {
        super(ErrorCode.REGISTER_FILE_NOT_FOUND);
    }

    public RegisterFileNotfoundException(String message) {
        super(message, ErrorCode.REGISTER_FILE_NOT_FOUND);
    }
}
