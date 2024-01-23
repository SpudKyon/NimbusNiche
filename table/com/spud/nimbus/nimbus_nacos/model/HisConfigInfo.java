package com.spud.nimbus.nimbus_nacos.model;

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
 * 多租户改造
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("his_config_info")
public class HisConfigInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private Long id;

    @TableId(value = "nid", type = IdType.AUTO)
    private Long nid;

    @TableField("data_id")
    private String dataId;

    @TableField("group_id")
    private String groupId;

    /**
     * app_name
     */
    @TableField("app_name")
    private String appName;

    @TableField("content")
    private String content;

    @TableField("md5")
    private String md5;

    @TableField("gmt_create")
    private LocalDateTime gmtCreate;

    @TableField("gmt_modified")
    private LocalDateTime gmtModified;

    @TableField("src_user")
    private String srcUser;

    @TableField("src_ip")
    private String srcIp;

    @TableField("op_type")
    private String opType;

    /**
     * 租户字段
     */
    @TableField("tenant_id")
    private String tenantId;

    /**
     * 秘钥
     */
    @TableField("encrypted_data_key")
    private String encryptedDataKey;


}
