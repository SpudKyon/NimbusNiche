package com.spud.nimbus.payment.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单支付记录
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("pay_info")
public class PayInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String DISTRIBUTED_ID_KEY = "nimbus-pay";

    /**
     * 支付单号
     */
    @TableId(value = "pay_id", type = IdType.AUTO)
    private Long payId;

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
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 本次支付关联的多个订单号
     */
    @TableField("order_ids")
    private String orderIds;

    /**
     * 外部订单流水号
     */
    @TableField("biz_pay_no")
    private String bizPayNo;

    /**
     * 系统类型 见SysTypeEnum
     */
    @TableField("sys_type")
    private Integer sysType;

    /**
     * 支付状态
     */
    @TableField("pay_status")
    private Integer payStatus;

    /**
     * 支付金额
     */
    @TableField("pay_amount")
    private Long payAmount;

    /**
     * 版本号
     */
    @TableField("version")
    private Integer version;

    /**
     * 回调内容
     */
    @TableField("callback_content")
    private String callbackContent;

    /**
     * 回调时间
     */
    @TableField("callback_time")
    private Date callbackTime;

    /**
     * 确认时间
     */
    @TableField("confirm_time")
    private LocalDateTime confirmTime;


}
