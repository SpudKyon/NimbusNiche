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
 * config_info_tag
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("config_info_tag")
public class ConfigInfoTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * data_id
     */
    @TableField("data_id")
    private String dataId;

    /**
     * group_id
     */
    @TableField("group_id")
    private String groupId;

    /**
     * tenant_id
     */
    @TableField("tenant_id")
    private String tenantId;

    /**
     * tag_id
     */
    @TableField("tag_id")
    private String tagId;

    /**
     * app_name
     */
    @TableField("app_name")
    private String appName;

    /**
     * content
     */
    @TableField("content")
    private String content;

    /**
     * md5
     */
    @TableField("md5")
    private String md5;

    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    @TableField("gmt_modified")
    private LocalDateTime gmtModified;

    /**
     * source user
     */
    @TableField("src_user")
    private String srcUser;

    /**
     * source ip
     */
    @TableField("src_ip")
    private String srcIp;


}
