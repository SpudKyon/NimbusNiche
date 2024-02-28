package com.spud.nimbus.api.product.vo;

import com.spud.nimbus.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/9
 */
@Data
@ToString
public class AttrValueVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	@Schema(description = "属性id")
	private Long attrValueId;

	@Schema(description = "属性ID")
	private Long attrId;

	@Schema(description = "属性值")
	private String value;

}
