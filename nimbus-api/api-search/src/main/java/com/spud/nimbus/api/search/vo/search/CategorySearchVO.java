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
public class CategorySearchVO {

	private static final long serialVersionUID = 1L;

	@Schema(description = "分类id")
	private Long categoryId;

	@Schema(description = "分类名称")
	private String name;

}
