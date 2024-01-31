package com.spud.nimbus.user.vo;

import com.spud.nimbus.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author spud
 * @date 2024/1/31
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class UserVO extends BaseVO {
  private static final long serialVersionUID = 1L;

  @Schema(description = "ID" )
  private Long userId;

  @Schema(description = "用户昵称" )
  private String nickName;

  @Schema(description = "头像图片路径" )
  private String pic;

  @Schema(description = "状态 1 正常 0 无效" )
  private Integer status;

  /**
   * openId list
   */
  private List<String> bizUserIdList;
}
