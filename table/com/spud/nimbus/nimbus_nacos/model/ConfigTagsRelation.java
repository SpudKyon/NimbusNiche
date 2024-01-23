package com.spud.nimbus.nimbus_nacos.model;

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
 * config_tag_relation
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("config_tags_relation")
public class ConfigTagsRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableField("id")
    private Long id;

    /**
     * tag_name
     */
    @TableField("tag_name")
    private String tagName;

    /**
     * tag_type
     */
    @TableField("tag_type")
    private String tagType;

    /**
     * data_id
     */
    @TableField("data_id")
    private String dataId;

    /**
     * group_id
     */
    @TableField("group_id")
    private String groupId;

    /**
     * tenant_id
     */
    @TableField("tenant_id")
    private String tenantId;

    @TableId(value = "nid", type = IdType.AUTO)
    private Long nid;


}
