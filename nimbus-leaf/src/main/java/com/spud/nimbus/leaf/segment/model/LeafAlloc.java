package com.spud.nimbus.leaf.segment.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
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
@Data
@Accessors(chain = true)
@TableName("leaf_alloc")
public class LeafAlloc implements Serializable {

  private static final long serialVersionUID = 1L;

  private String key;

  /**
   * 区分业务
   */
  @TableId(value = "biz_tag", type = IdType.AUTO)
  private String bizTag;

  /**
   * 该biz_tag目前所被分配的ID号段的最大值
   */
  @TableField("max_id")
  private Long maxId;

  /**
   * 每次分配的号段长度
   */
  @TableField("step")
  private Integer step;

  /**
   * 更新时间
   */
  @TableField("update_time")
  private LocalDateTime updateTime;

  /**
   * 描述
   */
  @TableField("description")
  private String description;

  /**
   * 每次getid时随机增加的长度，这样就不会有连续的id了
   */
  @TableField("random_step")
  private Integer randomStep;


}
