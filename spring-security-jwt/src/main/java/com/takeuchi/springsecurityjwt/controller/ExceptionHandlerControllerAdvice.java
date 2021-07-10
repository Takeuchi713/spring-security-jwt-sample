package com.takeuchi.springsecurityjwt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.takeuchi.springsecurityjwt.common.exception.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {

	/**
	 * status 400
	 *
	 * @param exception ユーザー未登録
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserNotFoundException.class)
	public Map<String, Object> userNotFoundException(UserNotFoundException ex) {
		log.warn(ex.getMessage());
		ex.printStackTrace();

		Map<String, Object> errorMap = new HashMap<>();
		errorMap.put("errcd", HttpStatus.BAD_REQUEST.value());
		errorMap.put("message", "user was not found");

		return errorMap;
	}
}
