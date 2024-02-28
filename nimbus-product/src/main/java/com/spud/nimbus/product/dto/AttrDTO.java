package com.spud.nimbus.product.dto;

import com.spud.nimbus.common.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/10
 */
@Data
@ToString
public class AttrDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	@Schema(description = "attr id")
	private Long attrId;

	@Schema(description = "店铺id")
	private Long shopId;

	@NotNull(message = "属性名称不能为空")
	@Schema(description = "属性名称")
	private String name;

	@Schema(description = "属性描述")
	private String desc;

	@Schema(description = "作为搜索参数 0:不需要，1:需要")
	private Integer searchType;

	@Schema(description = "属性类型 0:销售属性，1:基本属性")
	private Integer attrType;

	@Schema(description = "分类id列表")
	private List<Long> categoryIds;

	@Schema(description = "属性值列表")
	private List<AttrValueDTO> attrValues;

}
