package com.spud.nimbus.product.dto;

import com.spud.nimbus.common.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/10
 */
@Data
@ToString
public class SpuDetailDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	@Schema(description = "商品id")
	private Long spuId;

	@Schema(description = "商品详情")
	private String detail;

}
