package com.dpd.demo.exception;


import com.dpd.demo.error.AbstractRuntimeExceptionInheritance;
import com.dpd.demo.error.ErrorCode;

public class NonExistingPersonException extends AbstractRuntimeExceptionInheritance {

    public NonExistingPersonException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public static NonExistingPersonException ofPersonId(final ErrorCode errorCode, final String param) {
        final String message = String.format("Unable to get person with ID '%s' because of reason '%s' ", param, errorCode);

        return new NonExistingPersonException(message, errorCode);
    }

}
