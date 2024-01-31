package com.spud.nimbus.common.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author spud
 * @date 2024/1/30
 */
@Data
@ToString
public class ShopCartOrderMergerVO {

  @Schema(description = "商品总值" , requiredMode = Schema.RequiredMode.REQUIRED)
  private Long total;

  @Schema(description = "商品总数" , requiredMode = Schema.RequiredMode.REQUIRED)
  private Integer totalCount;

  @Schema(description = "配送类型 ：无需快递" )
  private Integer dvyType;

  @Schema(description = "过滤掉的商品项" , requiredMode = Schema.RequiredMode.REQUIRED)
  private List<ShopCartItemVO> filterShopItems;

  @Schema(description = "每个店铺的订单信息" , requiredMode = Schema.RequiredMode.REQUIRED)
  List<ShopCartOrderVO> shopCartOrders;

  @Schema(description = "用户地址" )
  private UserAddrVO userAddr;
}
