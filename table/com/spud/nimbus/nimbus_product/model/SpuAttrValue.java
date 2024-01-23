package com.spud.nimbus.nimbus_product.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品规格属性关联信息
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("spu_attr_value")
public class SpuAttrValue implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品属性值关联信息id
     */
    @TableId(value = "spu_attr_value_id", type = IdType.AUTO)
    private Long spuAttrValueId;

    /**
     * 商品id
     */
    @TableField("spu_id")
    private Long spuId;

    /**
     * 规格属性id
     */
    @TableField("attr_id")
    private Long attrId;

    /**
     * 规格属性名称
     */
    @TableField("attr_name")
    private String attrName;

    /**
     * 规格属性值id
     */
    @TableField("attr_value_id")
    private Long attrValueId;

    /**
     * 规格属性值名称
     */
    @TableField("attr_value_name")
    private String attrValueName;

    /**
     * 规格属性描述
     */
    @TableField("attr_desc")
    private String attrDesc;


}
