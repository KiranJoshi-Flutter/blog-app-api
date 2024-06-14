package com.example.springboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.springboot.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
//		String message = ex.getResourceName() + ex.getFieldName() + ex.getFieldValue();
		String message = ex.getMessage();
		ApiResponse response = new ApiResponse(message, false);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

}
