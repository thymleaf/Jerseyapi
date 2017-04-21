package com.topjavatutorial;

public class BaseEntity<T> {
	
	public static final String CODE = "code";
	public static final String MSG = "msg";
	public static final String DATA = "data";

	private String msg;

	private String code;

	private T data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
