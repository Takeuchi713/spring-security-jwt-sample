package com.takeuchi.springsecurityjwt.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.takeuchi.springsecurityjwt.common.CommonConstants;

@RestController
@RequestMapping(CommonConstants.API_BASE_PATH  + "/public")
public class PublicController {

	@GetMapping("/hello")
	public ResponseEntity<String> hello() {
		return new ResponseEntity<String>("hello", new HttpHeaders(), HttpStatus.OK);
	}
}


