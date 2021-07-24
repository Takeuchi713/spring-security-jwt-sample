package com.takeuchi.springsecurityjwt.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.takeuchi.springsecurityjwt.domain.model.dto.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		log.error(accessDeniedException.getMessage());

		ErrorResponse er = ErrorResponse.builder()
			.status(HttpStatus.FORBIDDEN.value())
			.error(HttpStatus.FORBIDDEN.getReasonPhrase())
			.message(accessDeniedException.getMessage())
			.path(request.getServletPath())
			.build();
		
		ObjectMapper om = new ObjectMapper();
		String json = er.toJson(om);
		
		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.setContentType(MediaType.APPLICATION_JSON.toString());
		response.getWriter().write(json);
	}
}
