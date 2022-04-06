package com.curso.poo.converter;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public class ApiError {

	private List<String> errors;
	
	public ApiError(String message) {
		this.errors = Arrays.asList(message);
	}
	
	public ApiError(List<String> messages) {
		this.errors = messages;
	}
}
