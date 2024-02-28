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
public class AttrValueDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	@Schema(description = "属性id")
	private Long attrValueId;

	@Schema(description = "属性ID")
	private Long attrId;

	@Schema(description = "属性值")
	private String value;

}
