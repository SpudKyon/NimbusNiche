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
public class AttrSearchVO {

	@Schema(description = "规格id")
	private Long attrId;

	@Schema(description = "规格名")
	private String attrName;

	@Schema(description = "规格值列表")
	private List<AttrValueSearchVO> attrValues;

}
