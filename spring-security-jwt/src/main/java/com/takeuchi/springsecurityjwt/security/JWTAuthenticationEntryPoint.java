package com.takeuchi.springsecurityjwt.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.takeuchi.springsecurityjwt.domain.model.dto.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		log.error(authException.getMessage());

		ErrorResponse er = ErrorResponse.builder()
			.status(HttpStatus.UNAUTHORIZED.value())
			.error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
			.message(authException.getMessage())
			.path(request.getServletPath())
			.build();

		ObjectMapper om = new ObjectMapper();
		String json = er.toJson(om);
		
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON.toString());
		response.getWriter().write(json);
	}
}
