package com.gxtec.api.util;

public class HttpCode {

	/**
	 * 请求成功
	 */
	public static final int OK = 200;

	/**
	 * 没有新文档
	 */
	public static final int NO_CONTENT = 204;

	/**
	 * 客户端请求的语法错误，服务器无法理解
	 */
	public static final int BAD_REQUEST = 400;

	/**
	 * 请求要求用户的身份认证
	 */
	public static final int UNAUTHORIZED = 401;

	/**
	 * 服务器理解请求客户端的请求，但是拒绝执行此请求
	 */
	public static final int FORBIDDED = 403;

	/**
	 * 服务器无法根据客户端的请求找到资源
	 */
	public static final int NOT_FOUND = 404;

}
