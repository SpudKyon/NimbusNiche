package com.spud.nimbus.api.search.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/10
 */
@Data
@ToString
public class EsPageDTO {

	public static final String ASC = "ASC";

	public static final String DESC = "DESC";

	/**
	 * 最大分页大小，如果分页大小大于500，则用500作为分页的大小。防止有人直接传入一个较大的数，导致服务器内存溢出宕机
	 */
	public static final Integer MAX_PAGE_SIZE = 500;

	/**
	 * 当前页
	 */
	@NotNull(message = "pageNum 不能为空")
	@Schema(description = "当前页", requiredMode = Schema.RequiredMode.REQUIRED)
	private Integer pageNum;

	@NotNull(message = "pageSize 不能为空")
	@Schema(description = "每页大小", requiredMode = Schema.RequiredMode.REQUIRED)
	private Integer pageSize;

	@Schema(description = "排序字段数组，用逗号分割")
	private String[] columns;

	@Schema(description = "排序字段方式，用逗号分割，ASC正序，DESC倒序")
	private String[] orders;

}
