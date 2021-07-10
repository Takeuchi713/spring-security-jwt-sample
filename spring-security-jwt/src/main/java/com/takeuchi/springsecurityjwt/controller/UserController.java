package com.takeuchi.springsecurityjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.takeuchi.springsecurityjwt.domain.model.entity.User;
import com.takeuchi.springsecurityjwt.domain.service.UserService;

@RestController
@RequestMapping("/api/v1/" + "user")
public class UserController {

	private final UserService userService;

	@Autowired
	UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/find/me")
	public ResponseEntity<User> findMe(){

		User user = userService.findMe();

		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<User>(user, headers, HttpStatus.OK);
	}
}
