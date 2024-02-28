package com.spud.nimbus.product.vo;

import com.spud.nimbus.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/10
 */
@Data
@ToString
@Schema(description = "购物车合计")
public class ShopCartAmountVO extends BaseVO {

	@Schema(description = "总额")
	private Long totalMoney;

	@Schema(description = "总计")
	private Long finalMoney;

	@Schema(description = "减额")
	private Long subtractMoney;

	@Schema(description = "商品数量")
	private Integer count;

}
