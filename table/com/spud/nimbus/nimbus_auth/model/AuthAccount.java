package com.spud.nimbus.nimbus_auth.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 统一账户信息
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("auth_account")
public class AuthAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 全平台用户唯一id
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Long uid;

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
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 创建ip
     */
    @TableField("create_ip")
    private String createIp;

    /**
     * 状态 1:启用 0:禁用 -1:删除
     */
    @TableField("status")
    private Integer status;

    /**
     * 用户类型见SysTypeEnum 0.普通用户系统 1.商家端 2平台端
     */
    @TableField("sys_type")
    private Integer sysType;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 所属租户
     */
    @TableField("tenant_id")
    private Long tenantId;

    /**
     * 是否是管理员
     */
    @TableField("is_admin")
    private Integer isAdmin;


}
