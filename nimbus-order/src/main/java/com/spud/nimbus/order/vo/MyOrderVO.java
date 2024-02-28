package com.spud.nimbus.order.vo;

import com.spud.nimbus.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Date;

/**
 * @author spud
 * @date 2024/2/25
 */
@Data
@ToString
@Schema(description = "我的订单")
public class MyOrderVO extends BaseVO {

	@Schema(description = "订单项", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<MyOrderItemVO> orderItems;

	@Schema(description = "订单号", requiredMode = Schema.RequiredMode.REQUIRED)
	private Long orderId;

	@Schema(description = "总价", requiredMode = Schema.RequiredMode.REQUIRED)
	private Long actualTotal;

	@Schema(description = "订单状态", requiredMode = Schema.RequiredMode.REQUIRED)
	private Integer status;

	@Schema(description = "配送类型 3：无需快递", requiredMode = Schema.RequiredMode.REQUIRED)
	private Integer deliveryType;

	@Schema(description = "店铺名称", requiredMode = Schema.RequiredMode.REQUIRED)
	private String shopName;

	@Schema(description = "店铺id", requiredMode = Schema.RequiredMode.REQUIRED)
	private Long shopId;

	@Schema(description = "订单创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
	private Date createTime;

	@Schema(description = "商品总数", requiredMode = Schema.RequiredMode.REQUIRED)
	private Integer allCount;

}
