package com.spud.nimbus.api.search.vo.search;

import com.spud.nimbus.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/10
 */
@Data
@ToString
public class BrandSearchVO extends BaseVO {

	@Schema(description = "品牌名称")
	private String brandName;

	@Schema(description = "品牌id")
	private Long brandId;

	@Schema(description = "品牌图片")
	private String brandImg;

}
