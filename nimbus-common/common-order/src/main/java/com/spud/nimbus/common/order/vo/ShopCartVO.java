package com.spud.nimbus.common.order.vo;

import com.spud.nimbus.common.vo.BaseVO;
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
public class ShopCartVO extends BaseVO {

	@Schema(description = "店铺ID", requiredMode = Schema.RequiredMode.REQUIRED)
	private Long shopId;

	@Schema(description = "店铺名称", requiredMode = Schema.RequiredMode.REQUIRED)
	private String shopName;

	@Schema(description = "店铺类型1自营店 2普通店")
	private Integer shopType;

	@Schema(description = "购物车商品信息")
	private List<ShopCartItemVO> shopCartItem;

	@Schema(description = "商品总值", requiredMode = Schema.RequiredMode.REQUIRED)
	private Long total;

	@Schema(description = "数量", requiredMode = Schema.RequiredMode.REQUIRED)
	private Integer totalCount;

}
