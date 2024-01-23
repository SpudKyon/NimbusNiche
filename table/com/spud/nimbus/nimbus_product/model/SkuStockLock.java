package com.spud.nimbus.nimbus_product.model;

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
 * 库存锁定信息
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sku_stock_lock")
public class SkuStockLock implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
     * 商品id
     */
    @TableField("spu_id")
    private Long spuId;

    /**
     * sku id
     */
    @TableField("sku_id")
    private Long skuId;

    /**
     * 订单id
     */
    @TableField("order_id")
    private Long orderId;

    /**
     * 状态-1已解锁 0待确定 1已锁定
     */
    @TableField("status")
    private Integer status;

    /**
     * 锁定库存数量
     */
    @TableField("count")
    private Integer count;


}
