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
public class SpuSkuAttrValueDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	@Schema(description = "商品sku销售属性关联信息id")
	private Integer spuSkuAttrId;

	@Schema(description = "SPU ID")
	private Long spuId;

	@Schema(description = "SKU ID")
	private Long skuId;

	@Schema(description = "销售属性ID")
	private Long attrId;

	@Schema(description = "销售属性名称")
	private String attrName;

	@Schema(description = "销售属性值ID")
	private Long attrValueId;

	@Schema(description = "销售属性值")
	private String attrValueName;

	@Schema(description = "状态 1:enable, 0:disable")
	private Integer status;

}
