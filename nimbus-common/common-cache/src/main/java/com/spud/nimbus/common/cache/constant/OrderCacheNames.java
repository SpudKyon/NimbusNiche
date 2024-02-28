package com.spud.nimbus.common.cache.constant;

/**
 * @author spud
 * @date 2024/1/30
 */
public interface OrderCacheNames {

	/**
	 * 前缀
	 */
	String ORDER_PREFIX = "nimbus_order:";

	/**
	 * 确认订单信息缓存
	 */
	String ORDER_CONFIRM_KEY = ORDER_PREFIX + "order:confirm";

	/**
	 * 订单uuid
	 */
	String ORDER_CONFIRM_UUID_KEY = ORDER_PREFIX + "order:uuid_confirm";

}
