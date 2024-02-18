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
 * 属性值信息
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Data
@ToString
@Accessors(chain = true)
@TableName("attr_value")
public class AttrValue extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 属性id
     */
    @TableId(value = "attr_value_id", type = IdType.AUTO)
    private Long attrValueId;

    /**
     * 属性ID
     */
    @TableField("attr_id")
    private Long attrId;

    /**
     * 属性值
     */
    @TableField("value")
    private String value;


}
