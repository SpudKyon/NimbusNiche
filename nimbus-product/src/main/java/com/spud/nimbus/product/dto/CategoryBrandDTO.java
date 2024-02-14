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
public class CategoryBrandDTO extends BaseDTO {
  private static final long serialVersionUID = 1L;

  private Long id;

  @Schema(description = "品牌id" )
  private Long brandId;

  @Schema(description = "分类id" )
  private Long categoryId;

}
