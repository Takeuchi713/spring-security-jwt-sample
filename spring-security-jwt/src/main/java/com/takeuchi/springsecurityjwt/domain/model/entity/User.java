package com.takeuchi.springsecurityjwt.domain.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "email", length = 100, nullable = false, unique = true)
	private String email;

	@Column(name = "roles", length = 100, nullable = false)
	private String roles;

	@Column(name = "password", length = 150, nullable = false)
	private String password;

	@Column(name = "is_active", nullable = false)
	private Boolean active;
}
