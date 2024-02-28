package com.spud.nimbus.search.constant;

/**
 * @author spud
 * @date 2024/2/26
 */
public enum SearchTypeEnum {

	/**
	 * 用户端搜索
	 */
	APP(1),

	/**
	 * 商家端、平台端搜索
	 */
	MULTISHOP(2),

	/**
	 * 商家端、平台端搜索
	 */
	PLATFORM(3);

	private final Integer value;

	public Integer value() {
		return value;
	}

	SearchTypeEnum(Integer value) {
		this.value = value;
	}

}
