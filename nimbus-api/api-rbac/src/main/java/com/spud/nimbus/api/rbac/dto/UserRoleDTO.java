package com.spud.nimbus.api.rbac.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/9
 */
@Data
@ToString
public class UserRoleDTO {

  /**
   * 用户id
   */
  @NotNull(message = "userId not null")
  private Long userId;


  /**
   * 角色id列表
   */
  @NotEmpty(message = "userId not null")
  private List<Long> roleIds;
}
