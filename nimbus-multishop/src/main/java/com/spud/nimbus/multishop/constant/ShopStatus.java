package com.spud.nimbus.multishop.constant;

/**
 * @author spud
 * @date 2024/2/23
 */
public enum ShopStatus {

	/**
	 * 未开通
	 */
	DELETE(-1),
	/**
	 * 停业中
	 */
	STOP(0),

	/**
	 * 营业中
	 */
	OPEN(1);

	private Integer num;

	ShopStatus(Integer num) {
		this.num = num;
	}

	public static ShopStatus instance(Integer value) {
		ShopStatus[] enums = values();
		for (ShopStatus statusEnum : enums) {
			if (statusEnum.value().equals(value)) {
				return statusEnum;
			}
		}
		return null;
	}

	public Integer value() {
		return num;
	}

}
