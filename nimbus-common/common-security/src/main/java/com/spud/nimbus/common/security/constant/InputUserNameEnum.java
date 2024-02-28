package com.spud.nimbus.common.security.constant;

/**
 * @author spud
 * @date 2024/2/17
 */
public enum InputUserNameEnum {

	/**
	 * 用户名
	 */
	USERNAME(1),

	/**
	 * 手机号
	 */
	PHONE(2),

	/**
	 * 邮箱
	 */
	EMAIL(3),;

	private final Integer value;

	public Integer value() {
		return value;
	}

	InputUserNameEnum(Integer value) {
		this.value = value;
	}

}