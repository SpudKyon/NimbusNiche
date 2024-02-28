package com.spud.nimbus.product.dto.shopcart;

import com.spud.nimbus.common.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/10
 */
@Data
@ToString
public class CheckShopCartItemDTO extends BaseDTO {

	@NotNull
	@Schema(description = "购物车ID", requiredMode = Schema.RequiredMode.REQUIRED)
	private Long shopCartItemId;

	@NotNull
	@Schema(description = "商品是否勾选 1:勾选 0:未勾选")
	private Integer isChecked;

}
