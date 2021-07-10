package com.takeuchi.springsecurityjwt.common.exception;

public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException(String message) {
		super(message);
	}
}
