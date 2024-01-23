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
 * 增加租户字段
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("config_info_aggr")
public class ConfigInfoAggr implements Serializable {

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
     * datum_id
     */
    @TableField("datum_id")
    private String datumId;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 修改时间
     */
    @TableField("gmt_modified")
    private LocalDateTime gmtModified;

    @TableField("app_name")
    private String appName;

    /**
     * 租户字段
     */
    @TableField("tenant_id")
    private String tenantId;


}
