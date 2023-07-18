package de.huk.schulung.spring.blog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNoSuchElementException() {
        /*
         * Erweiterungen:
         *  - Rückgabe eines Objektes -> JSON
         *    - ProblemDetail -> JSON (RFC-7807)
         *    - ResponseEntity
         *  - Exception als Methodenparameter
         */
    }

}
