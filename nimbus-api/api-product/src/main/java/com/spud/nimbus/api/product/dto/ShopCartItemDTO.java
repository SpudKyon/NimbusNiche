package com.spud.nimbus.api.product.dto;

import com.spud.nimbus.common.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/9
 */
@Data
@ToString
public class ShopCartItemDTO extends BaseDTO {

	@NotNull(message = "产品ID不能为空")
	@Schema(description = "产品ID", requiredMode = Schema.RequiredMode.REQUIRED)
	private Long spuId;

	@NotNull(message = "skuId不能为空")
	@Schema(description = "skuId", requiredMode = Schema.RequiredMode.REQUIRED)
	private Long skuId;

	@NotNull(message = "商品数量不能为空")
	@Min(value = 1, message = "商品数量不能为空")
	@Schema(description = "商品数量", requiredMode = Schema.RequiredMode.REQUIRED)
	private Integer count;

	@NotNull(message = "店铺id不能为空")
	@Schema(description = "店铺id", requiredMode = Schema.RequiredMode.REQUIRED)
	private Long shopId;

}
