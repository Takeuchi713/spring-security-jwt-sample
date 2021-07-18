package com.takeuchi.springsecurityjwt.security;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import com.takeuchi.springsecurityjwt.domain.model.entity.User;

import lombok.Getter;

//EntityのUserとUserDetailをマッピングさせるクラス
public class LoginUser extends org.springframework.security.core.userdetails.User {

	//Entity
	@Getter
	private final User user;

	public LoginUser(User user) {
		super(user.getName(), user.getPassword(), user.getActive(),
				true, true, true, convertGrandtedAuthorites(user.getRoles()));

		this.user= user;
	}

	private static Set<GrantedAuthority> convertGrandtedAuthorites(String roles){
		//roles => "ROLE_USER","ROLE_ADMIN"

		if (!StringUtils.hasText(roles)) {
			return Collections.emptySet();
		}

		Set<GrantedAuthority> authorites = Stream.of(roles.split(","))
			.map(String::trim)
			.map(SimpleGrantedAuthority::new)
			.collect(Collectors.toSet());

		return authorites;
	}
}
