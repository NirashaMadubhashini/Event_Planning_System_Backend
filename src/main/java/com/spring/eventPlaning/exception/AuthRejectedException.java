package com.spring.eventPlaning.exception;

public class AuthRejectedException extends BaseException {
    public AuthRejectedException() {
    }

    public AuthRejectedException(String message) {
        super(message);
    }

    public AuthRejectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthRejectedException(Throwable cause) {
        super(cause);
    }

    public AuthRejectedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
