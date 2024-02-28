package com.spud.nimbus.api.product.vo;

import com.spud.nimbus.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/9
 */
@Data
@ToString
public class SpuAttrValueVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	@Schema(description = "商品属性值关联信息id")
	private Long spuAttrValueId;

	@Schema(description = "商品id")
	private Long spuId;

	@Schema(description = "规格属性id")
	private Long attrId;

	@Schema(description = "规格属性名称")
	private String attrName;

	@Schema(description = "规格属性值id")
	private Long attrValueId;

	@Schema(description = "规格属性值名称")
	private String attrValueName;

	@Schema(description = "搜索类型 0:不需要，1:需要")
	private Integer searchType;

}
