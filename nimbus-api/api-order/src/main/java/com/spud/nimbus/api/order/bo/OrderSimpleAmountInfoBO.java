package com.spud.nimbus.api.order.bo;

import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/9
 */
@Data
@ToString
public class OrderSimpleAmountInfoBO {

	private Long orderId;

	private Long shopId;

	/**
	 * 实际总值
	 */
	private Long actualTotal;

	/**
	 * 订单状态
	 */
	private Integer status;

	/**
	 * 订单关闭原因
	 */
	private Integer closeType;

}
