package com.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {
 
	@Autowired
	private Environment env;
	
    @ExceptionHandler(value = { ConstraintViolationException.class})
    public String handleConflict(ConstraintViolationException ex, WebRequest request) {
        String bodyOfResponse = "no";
        return bodyOfResponse;
    }
    
    @ExceptionHandler(value = { MissingServletRequestParameterException.class})
    public String handleConflict(MissingServletRequestParameterException ex, WebRequest request) {
        String bodyOfResponse = env.getProperty("Test.ConnectingCitiesWithIncorrectParameternameMsg");
        return bodyOfResponse;
    }
}