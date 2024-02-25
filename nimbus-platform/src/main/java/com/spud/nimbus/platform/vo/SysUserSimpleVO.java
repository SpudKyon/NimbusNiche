package com.spud.nimbus.platform.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spud.nimbus.common.serializer.ImgJsonSerializer;
import com.spud.nimbus.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/25
 */
@Data
@ToString
public class SysUserSimpleVO extends BaseVO {

  /**
   * 昵称
   */
  @Schema(description = "昵称")
  private String nickName;

  /**
   * 头像
   */
  @Schema(description = "头像")
  @JsonSerialize(using = ImgJsonSerializer.class)
  private String avatar;

  private Integer isAdmin;

}
