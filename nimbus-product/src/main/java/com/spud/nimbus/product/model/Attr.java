package com.spud.nimbus.product.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.spud.nimbus.common.model.BaseModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 属性信息
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Data
@ToString
@Accessors(chain = true)
@TableName("attr")
public class Attr extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * attr id
     */
    @TableId(value = "attr_id", type = IdType.AUTO)
    private Long attrId;

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
     * 店铺Id
     */
    @TableField("shop_id")
    private Long shopId;

    /**
     * 属性名称
     */
    @TableField("name")
    private String name;

    /**
     * 属性描述
     */
    @TableField("desc")
    private String desc;

    /**
     * 0:不需要，1:需要
     */
    @TableField("search_type")
    private Integer searchType;

    /**
     * 0:销售属性，1:基本属性
     */
    @TableField("attr_type")
    private Integer attrType;

    /**
     * 属性值列表
     */
    private List<AttrValue> attrValues;
}
