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
 * 商品分组标签关联信息
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("spu_tag_reference")
public class SpuTagReference implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分组引用id
     */
    @TableId(value = "reference_id", type = IdType.AUTO)
    private Long referenceId;

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
     * 标签id
     */
    @TableField("tag_id")
    private Long tagId;

    /**
     * 商品id
     */
    @TableField("spu_id")
    private Long spuId;

    /**
     * 状态(1:正常,-1:删除)
     */
    @TableField("status")
    private Boolean status;

    /**
     * 排序
     */
    @TableField("seq")
    private Integer seq;


}
