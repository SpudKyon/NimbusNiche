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
public class ShopCartWithAmountVO {

  @Schema(description = "总额")
  private Long totalMoney;

  @Schema(description = "总计")
  private Long finalMoney;

  @Schema(description = "商品数量")
  private Integer count;

  @Schema(description = "多个店铺的购物车信息")
  private List<ShopCartVO> shopCarts;
}
