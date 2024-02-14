package com.spud.nimbus.product.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.spud.nimbus.common.model.BaseModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 购物车
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Data
@ToString
@Accessors(chain = true)
@TableName("shop_cart_item")
public class ShopCartItem extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "cart_item_id", type = IdType.AUTO)
    private Long cartItemId;

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
     * 店铺ID
     */
    @TableField("shop_id")
    private Long shopId;

    /**
     * 产品ID
     */
    @TableField("spu_id")
    private Long spuId;

    /**
     * SkuID
     */
    @TableField("sku_id")
    private Long skuId;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 购物车产品个数
     */
    @TableField("count")
    private Integer count;

    /**
     * 售价，加入购物车时的商品价格
     */
    @TableField("price_fee")
    private Long priceFee;

    /**
     * 是否已勾选
     */
    @TableField("is_checked")
    private Integer isChecked;


}
