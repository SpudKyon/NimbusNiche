package com.spud.nimbus.product.dto;

import com.spud.nimbus.common.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author spud
 * @date 2024/2/10
 */
@Data
@ToString
public class SpuExtensionDTO extends BaseDTO {
  private static final long serialVersionUID = 1L;

  @Schema(description = "商品扩展信息表id")
  private Long spuExtendId;

  @Schema(description = "创建时间")
  private Date createTime;

  @Schema(description = "更新时间")
  private Date updateTime;

  @Schema(description = "商品id")
  private Long spuId;

  @Schema(description = "销量")
  private Integer saleNum;

  @Schema(description = "实际库存")
  private Integer actualStock;

  @Schema(description = "锁定库存")
  private Integer lockStock;

  @Schema(description = "可售卖库存")
  private Integer stock;
}
