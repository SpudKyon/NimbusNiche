package com.spud.nimbus.order.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spud.nimbus.common.serializer.ImgJsonSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/25
 */
@Data
@ToString
@Schema(description = "我的订单-订单项")
public class MyOrderItemVO {

  @Schema(description = "商品图片", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonSerialize(using = ImgJsonSerializer.class)
  private String pic;

  @Schema(description = "商品名称", requiredMode = Schema.RequiredMode.REQUIRED)
  private String spuName;

  @Schema(description = "订单号", requiredMode = Schema.RequiredMode.REQUIRED)
  private Long orderId;

  @Schema(description = "商品数量", requiredMode = Schema.RequiredMode.REQUIRED)
  private Integer count;

  @Schema(description = "商品价格", requiredMode = Schema.RequiredMode.REQUIRED)
  private Long price;

  @Schema(description = "skuId", requiredMode = Schema.RequiredMode.REQUIRED)
  private Long skuId;

  @Schema(description = "skuName", requiredMode = Schema.RequiredMode.REQUIRED)
  private String skuName;

  @Schema(description = "订单项id", requiredMode = Schema.RequiredMode.REQUIRED)
  private Long orderItemId;

  @Schema(description = "商品id", requiredMode = Schema.RequiredMode.REQUIRED)
  private Long spuId;

}
