package com.spud.nimbus.order.service;

import com.spud.nimbus.order.model.OrderPayInfo;

/**
 * 订单支付记录服务类
 */
public interface OrderPayInfoService {

	/**
	 * 保存订单支付记录
	 * @param orderPayInfo 订单支付记录
	 */
	void save(OrderPayInfo orderPayInfo);

	/**
	 * 更新订单支付记录
	 * @param orderPayInfo 订单支付记录
	 */
	void update(OrderPayInfo orderPayInfo);

	/**
	 * 根据订单支付记录id删除订单支付记录
	 * @param payId
	 */
	void deleteById(Long payId);

}
