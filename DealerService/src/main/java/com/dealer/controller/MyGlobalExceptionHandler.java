package com.dealer.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.dealer.exception.DealerNotFoundException;
import com.dealer.model.ErrorResponse;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

	@ExceptionHandler(value = { DealerNotFoundException.class })
	public ErrorResponse farmerNotFound(DealerNotFoundException ex) {

		ErrorResponse error = new ErrorResponse();
		error.setStatusMessage(HttpStatus.NOT_FOUND);
		error.setDateTime(LocalDateTime.now());
		error.setMsg(ex.getMessage());
		return error;

	}

	@ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
	public ErrorResponse methodtNotSupport(HttpRequestMethodNotSupportedException ex) {

		ErrorResponse error = new ErrorResponse();
		error.setStatusMessage(HttpStatus.METHOD_NOT_ALLOWED);
		error.setDateTime(LocalDateTime.now());
		error.setMsg(ex.getMessage());
		return error;

	}

	@ExceptionHandler(value = { Exception.class })
	public ErrorResponse handleException(Exception ex) {

		ErrorResponse error = new ErrorResponse();
		error.setStatusMessage(HttpStatus.BAD_REQUEST);
		error.setDateTime(LocalDateTime.now());
		error.setMsg(ex.getMessage());
		return error;

	}

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	protected ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponse error = new ErrorResponse();
		error.setStatusMessage(status);
		error.setDateTime(LocalDateTime.now());
		error.setMsg(ex.getBindingResult().toString());
		return error ;
	}
}
