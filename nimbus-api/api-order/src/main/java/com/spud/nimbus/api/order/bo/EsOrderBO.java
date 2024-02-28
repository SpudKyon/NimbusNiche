package com.spud.nimbus.api.order.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author spud
 * @date 2024/2/9
 */

@Data
@ToString
public class EsOrderBO {

	@Schema(description = "订单项", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<EsOrderItemBO> orderItems;

	@Schema(description = "用户id", requiredMode = Schema.RequiredMode.REQUIRED)
	private Long userId;

	@Schema(description = "订单号", requiredMode = Schema.RequiredMode.REQUIRED)
	private Long orderId;

	@Schema(description = "总价", requiredMode = Schema.RequiredMode.REQUIRED)
	private Long actualTotal;

	@Schema(description = "订单状态", requiredMode = Schema.RequiredMode.REQUIRED)
	private Integer status;

	@Schema(description = "配送类型 :无需快递", requiredMode = Schema.RequiredMode.REQUIRED)
	private Integer deliveryType;

	@Schema(description = "店铺名称", requiredMode = Schema.RequiredMode.REQUIRED)
	private String shopName;

	@Schema(description = "店铺id", requiredMode = Schema.RequiredMode.REQUIRED)
	private Long shopId;

	@Schema(description = "订单创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
	private Date createTime;

	@Schema(description = "商品总数", requiredMode = Schema.RequiredMode.REQUIRED)
	private Integer allCount;

	@Schema(description = "收货人姓名")
	private String consignee;

	@Schema(description = "收货人手机号")
	private String mobile;

	/**
	 * 用户订单地址Id
	 */
	private Long orderAddrId;

	/**
	 * 总值
	 */
	private Long total;

	/**
	 * 支付方式 请参考枚举PayType
	 */
	private Integer payType;

	/**
	 * 订单关闭原因 1-超时未支付 4-买家取消 15-已通过货到付款交易
	 */
	private Integer closeType;

	/**
	 * 发货时间
	 */
	private Date updateTime;

	/**
	 * 付款时间
	 */
	private Date payTime;

	/**
	 * 发货时间
	 */
	private Date deliveryTime;

	/**
	 * 完成时间
	 */
	private Date finallyTime;

	/**
	 * 取消时间
	 */
	private Date cancelTime;

	/**
	 * 是否已支付，1.已支付0.未支付
	 */
	private Integer isPayed;

	/**
	 * 用户订单删除状态，0：没有删除， 1：回收站， 2：永久删除
	 */
	private Integer deleteStatus;

}
