package com.curso.poo.exception.advice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.curso.poo.converter.ApiError;

@RestControllerAdvice
public class MessageNotValid {

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError argumentInvalid(MethodArgumentNotValidException ex) {
		BindingResult messageBinding = ex.getBindingResult();
		List<String> message = messageBinding.getAllErrors().stream()
				.map(ObjectError -> ObjectError.getDefaultMessage())
				.collect(Collectors.toList());
		
		return new ApiError(message);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity reponseStatus(ResponseStatusException ex) {
		HttpStatus codErro = ex.getStatus();
		String msg = ex.getMessage();
		
		@SuppressWarnings("unused")
		ApiError apiError = new ApiError(msg);
		return new ResponseEntity(msg, codErro);
	}
}
