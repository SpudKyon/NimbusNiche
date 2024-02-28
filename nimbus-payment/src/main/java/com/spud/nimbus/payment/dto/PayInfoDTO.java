package com.spud.nimbus.payment.dto;

import com.spud.nimbus.common.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/26
 */
@Data
@ToString
public class PayInfoDTO extends BaseDTO {

	@NotEmpty(message = "订单号不能为空")
	@Schema(description = "订单号", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<Long> orderIds;

	@Schema(description = "支付完成回跳地址", requiredMode = Schema.RequiredMode.REQUIRED)
	private String returnUrl;

}
