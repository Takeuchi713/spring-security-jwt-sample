package com.takeuchi.springsecurityjwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.takeuchi.springsecurityjwt.common.CommonConstants;
import com.takeuchi.springsecurityjwt.security.JWTAccessDeniedHandler;
import com.takeuchi.springsecurityjwt.security.JWTAuthenticationEntryPoint;
import com.takeuchi.springsecurityjwt.security.JWTAuthenticationFilter;
import com.takeuchi.springsecurityjwt.security.JWTAuthorizationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			//swaggerが認証エラーとならない為の設定
			.antMatchers("/v2/api-docs",
				"/configuration/ui",
				"/swagger-resources/**",
				"/configuration/security",
				"/swagger-ui.html",
				"/webjars/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			//CSRFを許可
			.csrf().disable()
			//セッションをステートレスに
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			//リクエスト認証
			.authorizeRequests()
				//tokenなしでも許容するAPI
				.mvcMatchers(CommonConstants.API_BASE_PATH + "/public/**", "/swagger-ui.html/**","/h2-console/**")
					.permitAll()
				//ROLE_USER or ROLE_ADMIN権限が必要なAPI
				.mvcMatchers(CommonConstants.API_BASE_PATH + "/user/**")
					.hasAnyRole("USER","ADNMIN")
				//ROLE_ADMIN権限が必要なAPI
				.mvcMatchers(CommonConstants.API_BASE_PATH + "/admin/**")
					.hasRole("ADMIN")
				.anyRequest().authenticated()
			.and()
				//requestを認証しtokenを発行するfilter
				.addFilter(new JWTAuthenticationFilter(authenticationManager()))
				//tokenの承認を行うfilter
				.addFilterAfter(new JWTAuthorizationFilter(), JWTAuthenticationFilter.class)
			//エラーハンドリング
			.exceptionHandling()
				// ログインエラー時のハンドラー設定(未ログインでAPI実行時のエラーも)
				.accessDeniedHandler(new JWTAccessDeniedHandler())
				// 権限エラー時のハンドラー設定
				.authenticationEntryPoint(new JWTAuthenticationEntryPoint())
			.and()
			//h2-consoleへ接続するための設定
			.headers().frameOptions().disable();
	}

	@Bean
	//password encoder
	public BCryptPasswordEncoder bCryptPasswordEncoder () {
		return new BCryptPasswordEncoder(10);
	}
}