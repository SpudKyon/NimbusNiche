package com.spud.nimbus.api.search.vo.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/10
 */
@Data
@ToString
public class ProductSearchVO {

	@Schema(description = "店铺信息")
	private ShopInfoSearchVO shopInfo;

	@Schema(description = "规格属性列表")
	private List<AttrSearchVO> attrs;

	@Schema(description = "品牌列表信息")
	private List<BrandSearchVO> brands;

	@Schema(description = "spu列表信息")
	private List<SpuSearchVO> spus;

	@Schema(description = "分类列表信息")
	private List<CategorySearchVO> categorys;

}
