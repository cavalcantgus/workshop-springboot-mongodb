package com.cavalcantgus.workshopmongo.resources.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cavalcantgus.workshopmongo.services.exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

// Promove um tratamento unificado de exceções
@ControllerAdvice
public class ResourceExceptionHandler {

	// Manipulador de exceção que define o comportamento do código a um evento anômalo
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException error, HttpServletRequest request){
		HttpStatus statusCode = HttpStatus.NOT_FOUND;
		StandardError notFoundError = new StandardError(
				System.currentTimeMillis(), 
				statusCode.value(), 
				"Não encontrado", 
				error.getMessage(), 
				request.getRequestURI());
		
		return ResponseEntity.status(statusCode).body(notFoundError);
	}
}
