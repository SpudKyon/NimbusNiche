package com.spud.nimbus.common.order.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spud.nimbus.common.serializer.ImgJsonSerializer;
import com.spud.nimbus.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author spud
 * @date 2024/1/30
 */
@Data
@ToString
public class ShopCartItemVO extends BaseVO {

	@Schema(description = "加入购物车时间", requiredMode = Schema.RequiredMode.REQUIRED)
	private Date createTime;

	@Schema(description = "购物车ID", requiredMode = Schema.RequiredMode.REQUIRED)
	private Long cartItemId;

	@Schema(description = "店铺ID")
	private Long shopId;

	@Schema(description = "产品ID")
	private Long spuId;

	@Schema(description = "SkuID")
	private Long skuId;

	@Schema(description = "用户ID")
	private Long userId;

	@Schema(description = "购物车产品个数")
	private Integer count;

	@Schema(description = "是否已经勾选")
	private Integer isChecked;

	@Schema(description = "售价，加入购物车时的商品价格")
	private Long priceFee;

	@Schema(description = "当前商品价格")
	private Long skuPriceFee;

	@Schema(description = "当前总价格(商品价格 * 数量)")
	private Long totalPriceFee;

	@Schema(description = "当前总价格(商品价格 * 数量)(带小数)")
	private Long totalPrice;

	@Schema(description = "商品重量")
	private BigDecimal weight;

	@Schema(description = "商品体积")
	private BigDecimal volume;

	@Schema(description = "商品图片")
	@JsonSerialize(using = ImgJsonSerializer.class)
	private String imgUrl;

	@Schema(description = "总金额", requiredMode = Schema.RequiredMode.REQUIRED)
	private Long totalAmount;

	@Schema(description = "sku规格信息")
	private String skuName;

	@Schema(description = "spu名称")
	private String spuName;

}
