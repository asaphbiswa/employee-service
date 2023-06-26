package com.employeeapi.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class EmployeeNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 8540320898020730752L;
	
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
