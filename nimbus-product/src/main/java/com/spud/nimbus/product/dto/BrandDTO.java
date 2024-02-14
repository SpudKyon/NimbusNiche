package com.spud.nimbus.product.dto;

import com.spud.nimbus.common.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/10
 */
@Data
@ToString
public class BrandDTO extends BaseDTO{
  private static final long serialVersionUID = 1L;

  @Schema(description = "brand_id" )
  private Long brandId;

  @NotNull(message = "品牌名称不能为空")
  @Schema(description = "品牌名称" )
  private String name;

  @Schema(description = "品牌描述" )
  private String desc;

  @NotNull(message = "logo图片不能为空")
  @Schema(description = "品牌logo图片" )
  private String imgUrl;

  @NotNull(message = "首字母不能为空")
  @Schema(description = "检索首字母" )
  private String firstLetter;

  @NotNull(message = "序号不能为空")
  @Schema(description = "排序" )
  private Integer seq;

  @Schema(description = "状态 1:enable, 0:disable, -1:deleted" )
  private Integer status;

  @NotNull(message = "分类不能为空")
  @Schema(description = "分类" )
  private List<Long> categoryIds;
}
