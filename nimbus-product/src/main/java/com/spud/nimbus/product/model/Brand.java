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
 * 品牌信息
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Data
@ToString
@Accessors(chain = true)
@TableName("brand")
public class Brand extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * brand_id
     */
    @TableId(value = "brand_id", type = IdType.AUTO)
    private Long brandId;

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
     * 品牌名称
     */
    @TableField("name")
    private String name;

    /**
     * 品牌描述
     */
    @TableField("desc")
    private String desc;

    /**
     * 品牌logo图片
     */
    @TableField("img_url")
    private String imgUrl;

    /**
     * 检索首字母
     */
    @TableField("first_letter")
    private String firstLetter;

    /**
     * 排序
     */
    @TableField("seq")
    private Integer seq;

    /**
     * 状态 1:enable, 0:disable, -1:deleted
     */
    @TableField("status")
    private Integer status;


}
