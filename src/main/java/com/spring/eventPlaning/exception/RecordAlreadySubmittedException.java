package com.spring.eventPlaning.exception;

public class RecordAlreadySubmittedException extends BaseException {
    public RecordAlreadySubmittedException() {
    }

    public RecordAlreadySubmittedException(String message) {
        super(message);
    }

    public RecordAlreadySubmittedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecordAlreadySubmittedException(Throwable cause) {
        super(cause);
    }

    public RecordAlreadySubmittedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
