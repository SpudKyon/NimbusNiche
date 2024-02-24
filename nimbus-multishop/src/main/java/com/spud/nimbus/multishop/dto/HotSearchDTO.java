package com.spud.nimbus.multishop.dto;

import com.spud.nimbus.common.dto.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/24
 */
@Data
@ToString
public class HotSearchDTO  extends BaseDTO {
  private static final long serialVersionUID = 1L;

  @Schema(description = "主键")
  private Long hotSearchId;

  @Schema(description = "店铺ID 0为全局热搜")
  private Long shopId;

  @Schema(description = "内容")
  private String content;

  @Schema(description = "顺序")
  private Integer seq;

  @Schema(description = "状态 0下线 1上线")
  private Integer status;

  @Schema(description = "热搜标题")
  private String title;
}
