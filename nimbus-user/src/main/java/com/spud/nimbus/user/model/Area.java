package com.spud.nimbus.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.spud.nimbus.common.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 省市区地区信息
 * </p>
 *
 * @author spud
 * @since 2024-01-24
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("area")
public class Area extends BaseModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "area_id", type = IdType.AUTO)
  private Long areaId;

  /**
   * 地址
   */
  @TableField("area_name")
  private String areaName;

  /**
   * 上级地址
   */
  @TableField("parent_id")
  private Long parentId;

  /**
   * 等级（从1开始）
   */
  @TableField("level")
  private Integer level;


}
