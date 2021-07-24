package com.takeuchi.springsecurityjwt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.takeuchi.springsecurityjwt.domain.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailsServiceImp implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	UserDetailsServiceImp(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		log.info("user {} was accessed", email);

		return userRepository.findByEmail(email)
			.map(LoginUser::new)
			.orElseThrow(() -> new UsernameNotFoundException("User [" + email + "] was not found"));
	}
}
