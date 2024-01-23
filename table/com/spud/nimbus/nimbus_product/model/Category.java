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
 * 分类信息
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    @TableId(value = "category_id", type = IdType.AUTO)
    private Long categoryId;

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
     * 父ID
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 分类名称
     */
    @TableField("name")
    private String name;

    /**
     * 分类描述
     */
    @TableField("desc")
    private String desc;

    /**
     * 分类地址{parent_id}-{child_id},...
     */
    @TableField("path")
    private String path;

    /**
     * 状态 1:enable, 0:disable, -1:deleted
     */
    @TableField("status")
    private Integer status;

    /**
     * 分类图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 分类的显示图片
     */
    @TableField("img_url")
    private String imgUrl;

    /**
     * 分类层级 从0开始
     */
    @TableField("level")
    private Integer level;

    /**
     * 排序
     */
    @TableField("seq")
    private Integer seq;


}
