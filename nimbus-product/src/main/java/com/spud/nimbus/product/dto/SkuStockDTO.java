package com.spud.nimbus.product.dto;

import com.spud.nimbus.common.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/10
 */
@Data
@ToString
public class SkuStockDTO extends BaseDTO {
  private static final long serialVersionUID = 1L;

  @Schema(description = "库存id")
  private Long stockId;

  @Schema(description = "SKU ID")
  private Long skuId;

  @Schema(description = "库存")
  private Integer stock;
}
