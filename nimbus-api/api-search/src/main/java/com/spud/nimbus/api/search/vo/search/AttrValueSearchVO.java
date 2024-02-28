package com.spud.nimbus.api.search.vo.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/10
 */
@Data
@ToString
public class AttrValueSearchVO {

	@Schema(description = "规格值id")
	private Long attrValueId;

	@Schema(description = "规格值名称")
	private String attrValueName;

}
