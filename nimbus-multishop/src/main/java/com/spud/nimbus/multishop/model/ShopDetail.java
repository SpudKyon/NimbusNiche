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
 * 店铺详情
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("shop_detail")
public class ShopDetail implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 店铺id
   */
  @TableId(value = "shop_id", type = IdType.AUTO)
  private Long shopId;

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
   * 店铺名称
   */
  @TableField("shop_name")
  private String shopName;

  /**
   * 店铺简介
   */
  @TableField("intro")
  private String intro;

  /**
   * 店铺logo(可修改)
   */
  @TableField("shop_logo")
  private String shopLogo;

  /**
   * 店铺移动端背景图
   */
  @TableField("mobile_background_pic")
  private String mobileBackgroundPic;

  /**
   * 店铺状态(-1:已删除 0: 停业中 1:营业中)
   */
  @TableField("shop_status")
  private Integer shopStatus;

  /**
   * 营业执照
   */
  @TableField("business_license")
  private String businessLicense;

  /**
   * 身份证正面
   */
  @TableField("identity_card_front")
  private String identityCardFront;

  /**
   * 身份证反面
   */
  @TableField("identity_card_later")
  private String identityCardLater;

  /**
   * 店铺类型1自营店 2普通店
   */
  @TableField("type")
  private Integer type;


}
