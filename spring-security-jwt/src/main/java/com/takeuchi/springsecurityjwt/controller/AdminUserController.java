package com.takeuchi.springsecurityjwt.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.takeuchi.springsecurityjwt.common.CommonConstants;
import com.takeuchi.springsecurityjwt.domain.model.dto.UserRequest;
import com.takeuchi.springsecurityjwt.domain.model.entity.User;
import com.takeuchi.springsecurityjwt.domain.service.UserService;

@RestController
@RequestMapping(CommonConstants.API_BASE_PATH  + "/admin")
public class AdminUserController {

	private final UserService userService;

	@Autowired
	AdminUserController(UserService userService) {
		this.userService = userService;
	}

	/*
	 * idからUser情報を取得
	 */
	@GetMapping("/find/{id}")
	public ResponseEntity<User> findById(@PathVariable(name="id", required = true) Integer id){

		User user = userService.findById(id);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<User>(user, headers, HttpStatus.OK);
	}

	/*
	 * 全てのUser情報を種痘
	 */
	@GetMapping("/find/all")
	public ResponseEntity<List<User>> findAll(){

		List<User> users = userService.findAll();
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<List<User>>(users, headers, HttpStatus.OK);
	}


	/*
	 * Userを作成
	 */
	@PostMapping("/add")
	public ResponseEntity<User> addUser(@RequestBody @Valid UserRequest userRequest) {

		User user = userService.addUser(userRequest);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<User>(user, headers, HttpStatus.CREATED);
	}


	/*
	 * Userを更新
	 */
	@PutMapping("/update")
	public ResponseEntity<String> updateUser(@RequestBody @Valid UserRequest userRequest) {

		userService.updateUser(userRequest);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<String>("update successed", headers, HttpStatus.OK);
	}

	/*
	 * Userを削除
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable(name = "id", required = true) Integer id) {

		userService.deleteUserById(id);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<String>("user id: " + id + " was deleted", headers, HttpStatus.OK);
	}


}
