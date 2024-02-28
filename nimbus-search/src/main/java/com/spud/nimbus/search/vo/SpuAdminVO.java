package com.spud.nimbus.search.vo;

import com.spud.nimbus.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/26
 */
@Data
@ToString
public class SpuAdminVO extends BaseVO {

	@Schema(description = "商品id")
	private Long spuId;

	@Schema(description = "商品名称")
	private String spuName;

	@Schema(description = "商品介绍主图")
	private String mainImgUrl;

	@Schema(description = "店铺id")
	private Long shopId;

	@Schema(description = "店铺名称")
	private String shopName;

	@Schema(description = "商品售价")
	private Long priceFee;

	@Schema(description = "市场价，整数方式保存")
	private Long marketPriceFee;

	@Schema(description = "销量")
	private Integer saleNum;

	@Schema(description = "状态")
	private Integer spuStatus;

	@Schema(description = "库存")
	private Integer stock;

	@Schema(description = "序号")
	private Integer seq;

}
