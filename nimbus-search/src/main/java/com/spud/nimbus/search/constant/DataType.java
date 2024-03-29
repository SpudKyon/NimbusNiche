package com.spud.nimbus.search.constant;

/**
 * @author spud
 * @date 2024/2/26
 */
public enum DataType {

	/**
	 * 全部
	 */
	ALL(0),

	/**
	 * 销售中
	 */
	SALE(1),

	/**
	 * 已售罄
	 */
	SOLD_OUT(2),

	/**
	 * 已下架
	 */
	DISABLE(3);

	private final Integer value;

	public Integer value() {
		return value;
	}

	DataType(Integer value) {
		this.value = value;
	}

}
