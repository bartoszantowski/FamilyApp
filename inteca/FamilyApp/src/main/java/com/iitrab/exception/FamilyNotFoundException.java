package com.iitrab.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Generic business exception indicating that some resource could not be found.
 * Will resolve to the {@link HttpStatus#NOT_FOUND} if handled by the Spring's exception handler.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FamilyNotFoundException extends BusinessException {

    public FamilyNotFoundException(String message) {
        super(message);
    }

}
