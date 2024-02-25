package com.spud.nimbus.platform.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 平台用户
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 平台用户id
   */
  @TableId(value = "sys_user_id", type = IdType.AUTO)
  private Long sysUserId;

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
   * 昵称
   */
  @TableField("nick_name")
  private String nickName;

  /**
   * 头像
   */
  @TableField("avatar")
  private String avatar;

  /**
   * 员工编号
   */
  @TableField("code")
  private String code;

  /**
   * 联系方式
   */
  @TableField("phone_num")
  private String phoneNum;

  /**
   * 是否已经设置账号
   */
  @TableField("has_account")
  private Integer hasAccount;


}
