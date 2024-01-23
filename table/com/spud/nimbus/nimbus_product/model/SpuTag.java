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
 * 商品分组表
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("spu_tag")
public class SpuTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分组标签id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 分组标题
     */
    @TableField("title")
    private String title;

    /**
     * 店铺Id
     */
    @TableField("shop_id")
    private Long shopId;

    /**
     * 状态(1为正常,-1为删除)
     */
    @TableField("status")
    private Boolean status;

    /**
     * 默认类型(0:商家自定义,1:系统默认)
     */
    @TableField("is_default")
    private Boolean default;

    /**
     * 商品数量
     */
    @TableField("prod_count")
    private Long prodCount;

    /**
     * 列表样式(0:一列一个,1:一列两个,2:一列三个)
     */
    @TableField("style")
    private Integer style;

    /**
     * 排序
     */
    @TableField("seq")
    private Integer seq;

    /**
     * 删除时间
     */
    @TableField("delete_time")
    private LocalDateTime deleteTime;


}
