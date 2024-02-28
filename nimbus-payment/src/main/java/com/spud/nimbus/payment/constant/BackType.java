package com.spud.nimbus.payment.constant;

/**
 * @author spud
 * @date 2024/2/26
 */
public enum BackType {

	/**
	 * api
	 */
	API(0),
	/**
	 * 商家端
	 */
	SHOP(1),

	/**
	 * 平台端
	 */
	PLATFORM(2);

	private Integer num;

	BackType(Integer num) {
		this.num = num;
	}

	public static BackType instance(Integer value) {
		BackType[] enums = values();
		for (BackType statusEnum : enums) {
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