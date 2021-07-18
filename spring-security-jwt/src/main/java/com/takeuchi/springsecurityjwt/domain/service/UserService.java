package com.takeuchi.springsecurityjwt.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.takeuchi.springsecurityjwt.domain.model.dto.UserRequest;
import com.takeuchi.springsecurityjwt.domain.model.entity.User;

@Service
public interface UserService {

	/*
	 * tokenから判定した自分の情報を取得
	 */
	public User findMe();

	/*
	 * idに紐づくUser情報を取得
	 */
	public User findById(Integer id);

	/**
	 * すべてのUser情報をリストで取得
	 * @return List<User>
	 */
	public List<User> findAll();

	/**
	 * Userを作成
	 * @param userRequest
	 * @return created User
	 */
	public User addUser(UserRequest userRequest);

	/**
	 * Userを更新
	 * @param userRequest
	 */
	public void updateUser(UserRequest userRequest);

	/*
	 * idに紐づくUserを削除
	 */
	public void deleteUserById(Integer id);


	public User updateMe();

	public User deleteMe();
}
