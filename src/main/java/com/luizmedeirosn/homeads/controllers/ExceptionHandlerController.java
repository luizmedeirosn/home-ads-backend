package com.luizmedeirosn.homeads.controllers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.luizmedeirosn.homeads.shared.exceptions.DatabaseException;
import com.luizmedeirosn.homeads.shared.exceptions.ExceptionHandlerError;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ExceptionHandlerError> handleDatabaseException(DatabaseException e,
            HttpServletRequest request) {
        final String ERROR = "DATA BASE ERROR";
        final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
        final ExceptionHandlerError EXCEPTION = new ExceptionHandlerError(Instant.now(), STATUS.value(), ERROR,
                e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(STATUS).body(EXCEPTION);
    }

}
