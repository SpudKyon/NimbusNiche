package com.spud.nimbus.api.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/9
 */
@Data
@ToString
public class DeliveryOrderDTO {

	@Schema(description = "deliveryOrderId")
	private Long deliveryOrderId;

	@NotNull(message = "订单号不能为空")
	@Schema(description = "订单号", requiredMode = Schema.RequiredMode.REQUIRED)
	private Long orderId;

	@NotNull(message = "发货方式不能为空")
	@Schema(description = "发货方式", requiredMode = Schema.RequiredMode.REQUIRED)
	private Integer deliveryType;

}
