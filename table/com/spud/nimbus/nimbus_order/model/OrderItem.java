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
 * 订单项
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("order_item")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单项ID
     */
    @TableId(value = "order_item_id", type = IdType.AUTO)
    private Long orderItemId;

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
     * 订单id
     */
    @TableField("order_id")
    private Long orderId;

    /**
     * 分类id
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 产品ID
     */
    @TableField("spu_id")
    private Long spuId;

    /**
     * 产品SkuID
     */
    @TableField("sku_id")
    private Long skuId;

    /**
     * 用户Id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 购物车产品个数
     */
    @TableField("count")
    private Integer count;

    /**
     * 产品名称
     */
    @TableField("spu_name")
    private String spuName;

    /**
     * sku名称
     */
    @TableField("sku_name")
    private String skuName;

    /**
     * 产品主图片路径
     */
    @TableField("pic")
    private String pic;

    /**
     * 单个orderItem的配送类型3：无需快递
     */
    @TableField("delivery_type")
    private Integer deliveryType;

    /**
     * 加入购物车时间
     */
    @TableField("shop_cart_time")
    private LocalDateTime shopCartTime;

    /**
     * 产品价格
     */
    @TableField("price")
    private Long price;

    /**
     * 商品总金额
     */
    @TableField("spu_total_amount")
    private Long spuTotalAmount;


}
