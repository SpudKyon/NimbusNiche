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
 * 库存信息
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sku_stock")
public class SkuStock implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 库存id
     */
    @TableId(value = "stock_id", type = IdType.AUTO)
    private Long stockId;

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
     * SKU ID
     */
    @TableField("sku_id")
    private Long skuId;

    /**
     * 实际库存
     */
    @TableField("actual_stock")
    private Integer actualStock;

    /**
     * 锁定库存
     */
    @TableField("lock_stock")
    private Integer lockStock;

    /**
     * 可售卖库存
     */
    @TableField("stock")
    private Integer stock;


}
