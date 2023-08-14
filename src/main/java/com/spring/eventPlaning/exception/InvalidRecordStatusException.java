package com.spring.eventPlaning.exception;

public class InvalidRecordStatusException extends BaseException {
    public InvalidRecordStatusException() {
    }

    public InvalidRecordStatusException(String message) {
        super(message);
    }

    public InvalidRecordStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRecordStatusException(Throwable cause) {
        super(cause);
    }

    public InvalidRecordStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
