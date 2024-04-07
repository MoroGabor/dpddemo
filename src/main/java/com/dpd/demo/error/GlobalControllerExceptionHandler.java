package com.dpd.demo.error;

import com.dpd.demo.exception.NonExistingPersonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(NonExistingPersonException.class)
    public ResponseEntity<ApiError> handleNotFindPersonException(NonExistingPersonException exception, WebRequest request) {
        log.error("Non existing person exception occurred: {}", exception.getMessage(), exception);
        return checkExceptions(exception, request);
    }

    private ResponseEntity<ApiError> checkExceptions(AbstractRuntimeExceptionInheritance exception, WebRequest request) {
        if (exception != null) {
            exception.printStackTrace();
            log.warn(exception.getMessage(), exception);
            ApiError apiError = new ApiError(exception.getErrorCode());
            if (request != null) {
                try {
                    if (request instanceof ServletWebRequest) {
                        apiError.setRequestUrl(((ServletWebRequest) request).getRequest().getRequestURI());
                        apiError.setRequestType(((ServletWebRequest) request).getRequest().getMethod());
                    }
                } catch (Exception ex) {
                    log.warn(ex.getMessage());
                }
            }
            return ResponseEntity.status(exception.getErrorCode().getStatus()).body(apiError);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
