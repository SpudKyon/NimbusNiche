package com.spud.nimbus.biz.model;

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
 * 
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Data
@ToString
@Accessors(chain = true)
@TableName("attach_file_group")
public class AttachFileGroup extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "attach_file_group_id", type = IdType.AUTO)
    private Long attachFileGroupId;

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
