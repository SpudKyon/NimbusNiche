package com.spud.nimbus.platform.dto;

import com.spud.nimbus.common.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/25
 */
@Data
@ToString
public class SysConfigDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private Long id;

	@Schema(description = "key")
	private String paramKey;

	@Schema(description = "value")
	private String paramValue;

	@Schema(description = "备注")
	private String remark;

}
