package com.example.ohjelmistoprojekti1.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//ei toimi, kesken

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {
	   private static final long serialVersionUID = 1L;

	    public NotFoundException(String errorMessage) {
	        super(errorMessage);
	    }
}
