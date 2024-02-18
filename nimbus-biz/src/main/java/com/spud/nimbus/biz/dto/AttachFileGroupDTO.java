package com.spud.nimbus.biz.dto;

import com.spud.nimbus.common.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/18
 */
@Data
@ToString
public class AttachFileGroupDTO extends BaseDTO {

  private static final long serialVersionUID = 1L;

  private Long attachFileGroupId;

  @Schema(description = "店铺id")
  private Long shopId;

  @Schema(description = "分组名称")
  private String name;
}
