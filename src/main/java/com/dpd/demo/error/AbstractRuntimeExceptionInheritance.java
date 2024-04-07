package com.dpd.demo.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AbstractRuntimeExceptionInheritance extends RuntimeException {

    protected ErrorCode errorCode;
    protected String message;
    protected String param;

    public AbstractRuntimeExceptionInheritance(final String message, final ErrorCode errorCode) {
        this(message, errorCode, null);
    }

    public AbstractRuntimeExceptionInheritance(final String message, final ErrorCode errorCode, final String param) {
        this.message = message;
        this.errorCode = errorCode;
        this.param = param;
    }

}
