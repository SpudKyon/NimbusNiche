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
@TableName("branch_table")
public class BranchTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "branch_id", type = IdType.AUTO)
    private Long branchId;

    @TableField("xid")
    private String xid;

    @TableField("transaction_id")
    private Long transactionId;

    @TableField("resource_group_id")
    private String resourceGroupId;

    @TableField("resource_id")
    private String resourceId;

    @TableField("branch_type")
    private String branchType;

    @TableField("status")
    private Integer status;

    @TableField("client_id")
    private String clientId;

    @TableField("application_data")
    private String applicationData;

    @TableField("gmt_create")
    private LocalDateTime gmtCreate;

    @TableField("gmt_modified")
    private LocalDateTime gmtModified;


}
