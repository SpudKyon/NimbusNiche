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
 * 租户容量信息表
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tenant_capacity")
public class TenantCapacity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * Tenant ID
     */
    @TableField("tenant_id")
    private String tenantId;

    /**
     * 配额，0表示使用默认值
     */
    @TableField("quota")
    private Integer quota;

    /**
     * 使用量
     */
    @TableField("usage")
    private Integer usage;

    /**
     * 单个配置大小上限，单位为字节，0表示使用默认值
     */
    @TableField("max_size")
    private Integer maxSize;

    /**
     * 聚合子配置最大个数
     */
    @TableField("max_aggr_count")
    private Integer maxAggrCount;

    /**
     * 单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值
     */
    @TableField("max_aggr_size")
    private Integer maxAggrSize;

    /**
     * 最大变更历史数量
     */
    @TableField("max_history_count")
    private Integer maxHistoryCount;

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


}
