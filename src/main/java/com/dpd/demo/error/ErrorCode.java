package com.dpd.demo.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    NON_EXISTING_PERSON(ErrorType.VALIDATION, HttpStatus.NOT_FOUND);

    private final ErrorType errorType;

    private final HttpStatus status;

    ErrorCode(final ErrorType errorType, HttpStatus status) {
        this.errorType = errorType;
        this.status = status;
    }

}
