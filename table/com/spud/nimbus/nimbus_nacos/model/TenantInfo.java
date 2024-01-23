package com.spud.nimbus.nimbus_nacos.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * tenant_info
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tenant_info")
public class TenantInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * kp
     */
    @TableField("kp")
    private String kp;

    /**
     * tenant_id
     */
    @TableField("tenant_id")
    private String tenantId;

    /**
     * tenant_name
     */
    @TableField("tenant_name")
    private String tenantName;

    /**
     * tenant_desc
     */
    @TableField("tenant_desc")
    private String tenantDesc;

    /**
     * create_source
     */
    @TableField("create_source")
    private String createSource;

    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private Long gmtCreate;

    /**
     * 修改时间
     */
    @TableField("gmt_modified")
    private Long gmtModified;


}
