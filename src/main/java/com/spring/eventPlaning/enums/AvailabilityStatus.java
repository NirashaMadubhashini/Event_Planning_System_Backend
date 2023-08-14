package com.spring.eventPlaning.enums;

import com.spring.eventPlaning.constant.ExceptionMessageConstant;
import com.spring.eventPlaning.exception.InvalidRecordStatusException;

public enum AvailabilityStatus {
    AVAILABLE, UNAVAILABLE;

    public static AvailabilityStatus getAvailabilityStatus(String status) throws InvalidRecordStatusException {
        switch (status) {
            case "AVAILABLE":
                return AvailabilityStatus.AVAILABLE;
            case "UNAVAILABLE":
                return AvailabilityStatus.UNAVAILABLE;
            default:
                throw new InvalidRecordStatusException(ExceptionMessageConstant.INVALID_RECORD_STATUS);
        }
    }
}
