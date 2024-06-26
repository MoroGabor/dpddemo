package com.dpd.demo.error;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class ApiError {
    private final ErrorCode errorCode;

    private String requestUrl;

    private String requestType;

    private Instant timeStamp = Instant.now();

    public ApiError(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
