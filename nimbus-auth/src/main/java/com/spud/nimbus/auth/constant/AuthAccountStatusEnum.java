package com.spud.nimbus.auth.constant;

/**
 * @author spud
 * @date 2024/2/17
 */
public enum AuthAccountStatusEnum {

	/**
	 * 启用
	 */
	ENABLE(1),

	/**
	 * 禁用
	 */
	DISABLE(0),

	/**
	 * 删除
	 */
	DELETE(-1);

	private final Integer value;

	AuthAccountStatusEnum(Integer value) {
		this.value = value;
	}

	public Integer value() {
		return value;
	}

}
