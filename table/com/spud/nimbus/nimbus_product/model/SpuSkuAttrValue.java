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
 * 商品sku销售属性关联信息
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("spu_sku_attr_value")
public class SpuSkuAttrValue implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品sku销售属性关联信息id
     */
    @TableId(value = "spu_sku_attr_id", type = IdType.AUTO)
    private Integer spuSkuAttrId;

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
     * SPU ID
     */
    @TableField("spu_id")
    private Long spuId;

    /**
     * SKU ID
     */
    @TableField("sku_id")
    private Long skuId;

    /**
     * 销售属性ID
     */
    @TableField("attr_id")
    private Integer attrId;

    /**
     * 销售属性名称
     */
    @TableField("attr_name")
    private String attrName;

    /**
     * 销售属性值ID
     */
    @TableField("attr_value_id")
    private Integer attrValueId;

    /**
     * 销售属性值
     */
    @TableField("attr_value_name")
    private String attrValueName;

    /**
     * 状态 1:enable, 0:disable, -1:deleted
     */
    @TableField("status")
    private Integer status;


}
