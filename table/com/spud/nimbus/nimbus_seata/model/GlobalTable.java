package com.spud.nimbus.nimbus_seata.model;

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
 * 
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("global_table")
public class GlobalTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "xid", type = IdType.AUTO)
    private String xid;

    @TableField("transaction_id")
    private Long transactionId;

    @TableField("status")
    private Integer status;

    @TableField("application_id")
    private String applicationId;

    @TableField("transaction_service_group")
    private String transactionServiceGroup;

    @TableField("transaction_name")
    private String transactionName;

    @TableField("timeout")
    private Integer timeout;

    @TableField("begin_time")
    private Long beginTime;

    @TableField("application_data")
    private String applicationData;

    @TableField("gmt_create")
    private LocalDateTime gmtCreate;

    @TableField("gmt_modified")
    private LocalDateTime gmtModified;


}
