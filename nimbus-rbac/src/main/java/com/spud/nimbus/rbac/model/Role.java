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
 * 角色
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("role")
@ToString
public class Role implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 角色id
   */
  @TableId(value = "role_id", type = IdType.AUTO)
  private Long roleId;

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
   * 角色名称
   */
  @TableField("role_name")
  private String roleName;

  /**
   * 备注
   */
  @TableField("remark")
  private String remark;

  /**
   * 创建者ID
   */
  @TableField("create_user_id")
  private Long createUserId;

  /**
   * 业务类型 1 店铺菜单 2平台菜单
   */
  @TableField("biz_type")
  private Integer bizType;

  /**
   * 所属租户
   */
  @TableField("tenant_id")
  private Long tenantId;


}
