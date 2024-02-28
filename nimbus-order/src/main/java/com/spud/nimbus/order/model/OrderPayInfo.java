package com.spud.nimbus.order.model;

import com.spud.nimbus.common.model.BaseModel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单支付记录
 */
@Data
@ToString
public class OrderPayInfo extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 支付单号
	 */
	private Long payId;

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 外部订单流水号
	 */
	private String bizPayNo;

	/**
	 * 系统类型 见SysTypeEnum
	 */
	private Integer sysType;

	/**
	 * 支付状态
	 */
	private Integer payStatus;

	/**
	 * 支付金额
	 */
	private Long payAmount;

	/**
	 * 版本号
	 */
	private Integer version;

	/**
	 * 回调内容
	 */
	private String callbackContent;

	/**
	 * 回调时间
	 */
	private Date callbackTime;

	/**
	 * 确认时间
	 */
	private Date confirmTime;

}
