package com.spud.nimbus.platform.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spud.nimbus.common.serializer.ImgJsonSerializer;
import com.spud.nimbus.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/25
 */
@Data
@ToString
public class SysUserVO extends BaseVO {

  /**
   * sysUserId
   */
  @Schema(description = "平台用户id")
  private Long sysUserId;

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

  /**
   * 员工编号
   */
  @Schema(description = "员工编号")
  private String code;

  /**
   * 联系方式
   */
  @Schema(description = "联系方式")
  private String phoneNum;

  @Schema(description = "是否已经有账号了")
  private Integer hasAccount;

  @Schema(description = "平台id")
  private Long shopId;

  @Schema(description = "角色id列表")
  private List<Long> roleIds;
}
