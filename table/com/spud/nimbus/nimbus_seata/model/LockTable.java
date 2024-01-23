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
@TableName("lock_table")
public class LockTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "row_key", type = IdType.AUTO)
    private String rowKey;

    @TableField("xid")
    private String xid;

    @TableField("transaction_id")
    private Long transactionId;

    @TableField("branch_id")
    private Long branchId;

    @TableField("resource_id")
    private String resourceId;

    @TableField("table_name")
    private String tableName;

    @TableField("pk")
    private String pk;

    @TableField("gmt_create")
    private LocalDateTime gmtCreate;

    @TableField("gmt_modified")
    private LocalDateTime gmtModified;


}
