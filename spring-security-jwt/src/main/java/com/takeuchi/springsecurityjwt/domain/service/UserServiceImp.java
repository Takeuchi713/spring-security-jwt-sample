package com.takeuchi.springsecurityjwt.domain.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.takeuchi.springsecurityjwt.common.CommonConstants;
import com.takeuchi.springsecurityjwt.common.exception.UserNotFoundException;
import com.takeuchi.springsecurityjwt.domain.model.dto.UserRequest;
import com.takeuchi.springsecurityjwt.domain.model.entity.User;
import com.takeuchi.springsecurityjwt.domain.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	private final UserRepository userRepository;
	private final HttpServletRequest servletRequest;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	UserServiceImp(UserRepository userRepository, HttpServletRequest servletRequest,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.servletRequest = servletRequest;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	@Transactional(readOnly = true)
	public User findMe() {
		// filterで追加したidを取得
		Integer id = getId();
		if(id == null) {
			throw new UserNotFoundException("no id");
		}
		return userRepository.findById(id)
			.orElseThrow(() -> new UserNotFoundException("user with id: " + id + "was not found"));
	}

	@Override
	@Transactional
	public User updateMe(UserRequest userRequest) {
		Integer id = getId();
		if(id == null) {
			throw new UserNotFoundException("no id");
		}
		User updateUser = userRepository.findById(id)
			.orElseThrow(() -> new UserNotFoundException("\"user with id: \" + id + \"was not found\""));
		
		updateUser.setEmail(userRequest.getEmail());
		updateUser.setName(userRequest.getName());
		updateUser.setRoles(userRequest.getRoles());
		updateUser.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
		updateUser.setActive(userRequest.getIs_active());
		userRepository.save(updateUser);
		return updateUser;
	}

	@Override
	@Transactional
	public void deleteMe() {
		Integer id = getId();
		if (id == null || userRepository.findById(id).get() == null) {
			throw new UserNotFoundException("no user was found");
		}
		userRepository.deleteById(id);
	}


	@Override
	@Transactional(readOnly = true)
	public User findById(Integer id) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new UserNotFoundException("user with id: " + id + "was not found"));
		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		List<User> users = userRepository.findAll();
		return users;
	}

	@Override
	@Transactional
	public User addUser(UserRequest userRequest) {
		User newUser = new User();
		newUser.setName(userRequest.getName());
		newUser.setEmail(userRequest.getEmail());
		newUser.setRoles(userRequest.getRoles());
		newUser.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
		newUser.setActive(userRequest.getIs_active());
		User user = userRepository.save(newUser);
		return user;
	}

	@Override
	@Transactional
	public void updateUser(UserRequest userRequest) {
		if(userRequest.getId() == null) {
			throw new ValidationException("id must not be null");
		}
		User updateUser = userRepository.findById(userRequest.getId())
				.orElseThrow(() -> new UserNotFoundException("user by id: " + userRequest.getId() + " was not found"));

		updateUser.setEmail(userRequest.getEmail());
		updateUser.setName(userRequest.getName());
		updateUser.setRoles(userRequest.getRoles());
		updateUser.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
		updateUser.setActive(userRequest.getIs_active());
		userRepository.save(updateUser);
	}

	@Override
	@Transactional
	public void deleteUserById(Integer id) {
		if(id == null) {
			throw new ValidationException("id must not be null");
		}
		userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("user by id: " + id + " was not found"));
		userRepository.deleteById(id);;
	}
	
	
	private Integer getId() {
		Integer id = null;
		try {
			String idStr = (String) servletRequest.getAttribute(CommonConstants.USER_ID);
			id = Integer.valueOf(idStr);
		}catch(Exception e) {
			return id;
		}
		return id;
	}
}
