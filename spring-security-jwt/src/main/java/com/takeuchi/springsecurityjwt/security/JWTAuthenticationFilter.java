package com.takeuchi.springsecurityjwt.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.takeuchi.springsecurityjwt.common.CommonConstants;
import com.takeuchi.springsecurityjwt.domain.model.dto.LoginRequest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/*
 * UsernamePasswordAuthenticationFilterはデフォルトでは
 * /loginへPOSTされると username,passwordのKeyででログイン認証を行う。
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
//	private final PasswordEncoder passwordEncoder;


	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {

		this.authenticationManager = authenticationManager;
//		this.passwordEncoder = passwordEncoder;

		//デフォルトのpathを変更
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(CommonConstants.LOGIN_URL, "POST"));

		//デフォルトのパラメーターを変更
		setUsernameParameter(CommonConstants.EMAIL);
		setPasswordParameter(CommonConstants.PASSWORD);
	}

	@Override
	//ログイン認証処理
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

			Authentication authentication = new UsernamePasswordAuthenticationToken(
					loginRequest.getEmail(), //principal
					loginRequest.getPassword() //credencial
			);

			//リクエストの検証結果を返す
			return authenticationManager.authenticate(authentication);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	//ログイン成功後にtokenを作成
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String token = Jwts.builder()
			.setSubject(((LoginUser)authResult.getPrincipal()).getUser().getId().toString()) //idを埋め込む
			.claim(CommonConstants.AUTHORITES, authResult.getAuthorities()) //権限を埋め込む
			.setIssuedAt(new Date()) //token発行日
			.setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) //tokenの有効期限を10分に設定
			.signWith(Keys.hmacShaKeyFor(CommonConstants.SECRET_KEY.getBytes())) //暗号化のkey
			.compact();

		response.addHeader(CommonConstants.AUTHORIZED_HEADER, CommonConstants.TOKEN_PREFIX + token);
	}

}
