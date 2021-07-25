package com.takeuchi.springsecurityjwt.domain.model.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
	
	
	private int status;
	private String error;
	private String message;
	private String path;
	
	public String toJson(ObjectMapper om) throws JsonProcessingException{
		return om.writeValueAsString(this);	}
}
