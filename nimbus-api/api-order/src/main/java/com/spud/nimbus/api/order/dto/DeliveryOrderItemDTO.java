package com.spud.nimbus.api.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/9
 */
@Data
@ToString
public class DeliveryOrderItemDTO {

  @Schema(description = "id")
  private Long orderItemId;

  @Schema(description = "商品图片")
  private String pic;

  @Schema(description = "商品名称")
  private String spuName;

  @Schema(description = "发货改变的数量")
  private Integer changeNum;

}
