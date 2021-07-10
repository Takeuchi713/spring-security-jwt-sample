package com.takeuchi.springsecurityjwt.domain.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserRequest {

	private Integer id;

	@NotBlank
	private String name;

	@Email
	private String email;

	@NotBlank
	private String roles;

	@NotBlank
	private String password;

	@NotNull
	private Boolean is_active;

}
