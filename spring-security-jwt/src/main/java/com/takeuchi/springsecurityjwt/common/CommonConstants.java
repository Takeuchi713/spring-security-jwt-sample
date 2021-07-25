package com.takeuchi.springsecurityjwt.common;

public class CommonConstants {

	public static final String API_BASE_PATH = "/api/v1" ;

	public static final String LOGIN_URL = API_BASE_PATH + "/login";

	public static final String EMAIL = "email";

	public static final String PASSWORD = "password";

	public static final String SECRET_KEY = "secretKeyForSpringSecurity_secretKeyForSpringSecurity_secretKeyForSpringSecurity";

	public static final String AUTHORITES = "authorites";

	public static final String AUTHORIZED_HEADER = "X-Authenticated-token";

	public static final String TOKEN_PREFIX = "Bearer ";
	
	public static final String USER_ID = "id";

}
