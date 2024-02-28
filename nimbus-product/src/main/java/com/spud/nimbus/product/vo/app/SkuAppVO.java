package com.spud.nimbus.product.vo.app;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spud.nimbus.common.serializer.ImgJsonSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/10
 */
@Data
@ToString
public class SkuAppVO {

	@Schema(description = "属性id")
	private Long skuId;

	@Schema(description = "SPU id")
	private Long spuId;

	@Schema(description = "sku名称")
	private String skuName;

	@JsonSerialize(using = ImgJsonSerializer.class)
	@Schema(description = "banner图片")
	private String imgUrl;

	@Schema(description = "售价，整数方式保存")
	private Long priceFee;

	@Schema(description = "市场价，整数方式保存")
	private Long marketPriceFee;

	@Schema(description = "库存")
	private Integer stock;

	@Schema(description = "属性")
	private String properties;

}
