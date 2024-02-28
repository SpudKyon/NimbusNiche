package com.spud.nimbus.search.constant;

/**
 * @author spud
 * @date 2024/2/26
 */
public enum EsIndexEnum {

	/**
	 * 商品
	 */
	PRODUCT("product"),

	/**
	 * 订单
	 */
	ORDER("order"),

	;

	private final String value;

	public String value() {
		return value;
	}

	EsIndexEnum(String value) {
		this.value = value;
	}

}
