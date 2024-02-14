package com.spud.nimbus.api.product.vo;

import com.spud.nimbus.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author spud
 * @date 2024/2/9
 */
@Data
@ToString
public class SkuVO extends BaseVO {
	private static final long serialVersionUID = 1L;

	@Schema(description = "属性id" )
	private Long skuId;

	@Schema(description = "SPU id" )
	private Long spuId;

	@Schema(description = "多个销售属性值id逗号分隔" )
	private String attrs;

	@Schema(description = "sku名称" )
	private String skuName;

	@Schema(description = "banner图片" )
	private String imgUrl;

	@Schema(description = "售价，整数方式保存" )
	private Long priceFee;

	@Schema(description = "市场价，整数方式保存" )
	private Long marketPriceFee;

	@Schema(description = "状态 1:enable, 0:disable, -1:deleted" )
	private Integer status;

	@Schema(description = "库存" )
	private Integer stock;

	@Schema(description = "商品编码" )
	private String partyCode;

	@Schema(description = "商品条形码" )
	private String modelId;

	@Schema(description = "商品重量" )
	private BigDecimal weight;

	@Schema(description = "商品体积" )
	private BigDecimal volume;

	@Schema(description = "当前sku规格列表" )
	private List<SpuSkuAttrValueVO> spuSkuAttrValues;
}
