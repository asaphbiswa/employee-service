package com.employeeapi.controller;

import com.employeeapi.exception.EmployeeNotFoundException;
import com.employeeapi.model.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = EmployeeNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(EmployeeNotFoundException exception) {
        log.warn(exception.getMessage());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .errorMessage(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
