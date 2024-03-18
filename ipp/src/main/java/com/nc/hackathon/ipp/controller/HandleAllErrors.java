package com.nc.hackathon.ipp.controller;

import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HandleAllErrors {

	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler({SpelEvaluationException.class, Exception.class})
	public String handleGeneralErrors() {
		
		return "errorPage";
		
	}
}
