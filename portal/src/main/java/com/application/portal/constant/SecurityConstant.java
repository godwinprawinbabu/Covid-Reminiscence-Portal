package com.application.portal.constant;

public class SecurityConstant {
	public static final long EXPIRATION_TIME = 432_000_000; // 5 days expressed in milliseconds
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String JWT_TOKEN_HEADER = "Jwt-Token";
	public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
	public static final String HCL = "HCL";
	public static final String HCL_ADMINISTRATION = "HCL User Management Portal";
	public static final String AUTHORITIES = "authorities";
	public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
	public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
	public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
	public static final String[] PUBLIC_URLS = { "/user/login", "/user/register", "/user/resetpassword/{email}", "/user/changepassword/{email}",
			"/centres/add", "/centres/find/{state}/{district}", "/centres/list", "/doses/{centresId}/add",
			"/doses/find/{centresId}/{date}", "/doses/update", "/centres/delete/{centresId}",
			"/doses/delete/{dosesId}" , "/appointment/add"};
	// public static final String[] PUBLIC_URLS = { "**" };
}
