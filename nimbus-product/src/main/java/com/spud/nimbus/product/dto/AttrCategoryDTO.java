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
public class AttrCategoryDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	@Schema(description = "属性与分类关联id")
	private Long attrCategoryId;

	@Schema(description = "分类id")
	private Long categoryId;

	@Schema(description = "属性id")
	private Long attrId;

}
