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
public class SkuStockLockVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	@Schema(description = "id")
	private Long id;

	@Schema(description = "商品id")
	private Long spuId;

	@Schema(description = "sku id")
	private Long skuId;

	@Schema(description = "订单id")
	private Long orderId;

	@Schema(description = "锁定库存数量")
	private Integer count;

	@Schema(description = "状态-1已解锁 0待确定 1已锁定")
	private Integer status;

}
