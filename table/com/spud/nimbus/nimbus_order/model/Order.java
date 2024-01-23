package com.spud.nimbus.nimbus_order.model;

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
 * 订单信息
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;

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
     * 店铺id
     */
    @TableField("shop_id")
    private Long shopId;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 配送类型：无需快递
     */
    @TableField("delivery_type")
    private Integer deliveryType;

    /**
     * 店铺名称
     */
    @TableField("shop_name")
    private String shopName;

    /**
     * 总值
     */
    @TableField("total")
    private Long total;

    /**
     * 订单状态 1:待付款 2:待发货 3:待收货(已发货) 5:成功 6:失败
     */
    @TableField("status")
    private Integer status;

    /**
     * 订单商品总数
     */
    @TableField("all_count")
    private Integer allCount;

    /**
     * 付款时间
     */
    @TableField("pay_time")
    private LocalDateTime payTime;

    /**
     * 发货时间
     */
    @TableField("delivery_time")
    private LocalDateTime deliveryTime;

    /**
     * 完成时间
     */
    @TableField("finally_time")
    private LocalDateTime finallyTime;

    /**
     * 结算时间
     */
    @TableField("settled_time")
    private LocalDateTime settledTime;

    /**
     * 取消时间
     */
    @TableField("cancel_time")
    private LocalDateTime cancelTime;

    /**
     * 是否已支付，1.已支付0.未支付
     */
    @TableField("is_payed")
    private Boolean payed;

    /**
     * 订单关闭原因 1-超时未支付 4-买家取消 15-已通过货到付款交易
     */
    @TableField("close_type")
    private Integer closeType;

    /**
     * 用户订单删除状态，0：没有删除， 1：回收站， 2：永久删除
     */
    @TableField("delete_status")
    private Integer deleteStatus;

    /**
     * 订单版本号，每处理一次订单，版本号+1
     */
    @TableField("version")
    private Integer version;

    /**
     * 用户订单地址id
     */
    @TableField("order_addr_id")
    private Long orderAddrId;


}
