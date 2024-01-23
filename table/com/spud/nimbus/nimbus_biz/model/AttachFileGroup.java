package com.spud.nimbus.nimbus_biz.model;

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
 * 
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("attach_file_group")
public class AttachFileGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "attach_file_group_id", type = IdType.AUTO)
    private Long attachFileGroupId;

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
     * 分组名称
     */
    @TableField("name")
    private String name;

    /**
     * 1:图片 2:视频 3:文件
     */
    @TableField("type")
    private Integer type;


}
