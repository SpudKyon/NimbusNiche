package com.spud.nimbus.product.vo;

import com.spud.nimbus.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.io.Serial;

/**
 * @author spud
 * @date 2024/2/10
 */
@Data
@ToString
public class CategoryBrandVO extends BaseVO {

	@Serial
	private static final long serialVersionUID = 1L;

	private Long id;

	@Schema(description = "品牌id")
	private Long brandId;

	@Schema(description = "分类id")
	private Long categoryId;

}
