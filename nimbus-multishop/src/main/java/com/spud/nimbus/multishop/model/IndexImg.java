package com.spud.nimbus.multishop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 轮播图
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("index_img")
public class IndexImg implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId(value = "img_id", type = IdType.AUTO)
  private Long imgId;

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
   * 店铺ID
   */
  @TableField("shop_id")
  private Long shopId;

  /**
   * 图片
   */
  @TableField("img_url")
  private String imgUrl;

  /**
   * 状态 1:enable, 0:disable
   */
  @TableField("status")
  private Integer status;

  /**
   * 顺序
   */
  @TableField("seq")
  private Integer seq;

  /**
   * 关联商品id
   */
  @TableField("spu_id")
  private Long spuId;

  /**
   * 图片类型 0:小程序
   */
  @TableField("img_type")
  private Integer imgType;


}
