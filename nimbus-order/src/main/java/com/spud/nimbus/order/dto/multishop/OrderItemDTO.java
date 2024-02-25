package com.spud.nimbus.order.dto.multishop;

import com.spud.nimbus.common.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/25
 */
@Data
@ToString
public class OrderItemDTO extends BaseDTO {
  private static final long serialVersionUID = 1L;

  @Schema(description = "订单项ID")
  private Long orderItemId;

  @Schema(description = "变化金额", requiredMode = Schema.RequiredMode.REQUIRED)
  private Long changeAmount;
}
