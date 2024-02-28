package com.spud.nimbus.api.rbac.constant;

import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/9
 */
public enum HttpMethodEnum {

	/**
	 * GET
	 */
	GET(1, "GET"),

	/**
	 * POST
	 */
	POST(2, "POST"),

	/**
	 * PUT
	 */
	PUT(3, "PUT"),

	/**
	 * DELETE
	 */
	DELETE(4, "DELETE"),;

	private final Integer value;

	private final String method;

	public Integer value() {
		return value;
	}

	public String method() {
		return this.method;
	}

	HttpMethodEnum(Integer value, String method) {
		this.value = value;
		this.method = method;
	}

	public static HttpMethodEnum valueOf(Integer value) {
		for (HttpMethodEnum httpMethodEnum : values()) {
			if (Objects.equals(httpMethodEnum.value(), value)) {
				return httpMethodEnum;
			}
		}
		return null;
	}

}