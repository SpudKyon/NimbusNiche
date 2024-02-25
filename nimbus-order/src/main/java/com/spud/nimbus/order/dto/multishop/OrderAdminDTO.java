package com.spud.nimbus.order.dto.multishop;

import com.spud.nimbus.common.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/25
 */
@Data
@ToString
public class OrderAdminDTO extends BaseDTO {

  @Schema(description = "订单id")
  private Long orderId;

  @Schema(description = "店铺id")
  private Long shopId;

  @NotNull(message = "配送类型不能为空")
  @Schema(description = "配送类型 3：无需快递")
  private Integer dvyType;
}
