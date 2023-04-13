package com.dku.mentoring.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 최재민
 */
@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleApplicationException(final ApplicationException exception) {
        log.error("handleApplicationException", exception);
        final ErrorCode errorCode = exception.getErrorCode();
        final ErrorResponse response = ErrorResponse.from(errorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getHttpStatus()));
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<ErrorResponse> handleIllegalArgumentException(final IllegalArgumentException exception) {
        log.error("handleIllegalArgumentException", exception);
        final ErrorResponse response = ErrorResponse.from(ErrorCode.TOKEN_NOT_VALIDATED);
        return new ResponseEntity<>(response, HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
    }
}
