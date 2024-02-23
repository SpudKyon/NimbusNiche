package com.spud.nimbus.rbac.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户与角色对应关系
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user_role")
@ToString
public class UserRole implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 关联id
   */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  /**
   * 创建时间
   */
  @TableField("create_time")
  private LocalDateTime createTime;

  /**
   * 更新时间
   */
  @TableField("update_time")
  private LocalDateTime updateTime;

  /**
   * 用户ID
   */
  @TableField("user_id")
  private Long userId;

  /**
   * 角色ID
   */
  @TableField("role_id")
  private Long roleId;


}
