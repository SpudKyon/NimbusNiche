package com.spud.nimbus.search.bo;

import cn.throwx.canal.gule.annotation.CanalModel;
import cn.throwx.canal.gule.common.FieldNamingPolicy;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/26
 */
@CanalModel(database = "nimbus_product", table = "spu", fieldNamingPolicy = FieldNamingPolicy.LOWER_UNDERSCORE)
@Data
@ToString
public class SpuBO {
  /**
   * spu id
   */
  private Long spuId;

  /**
   * 品牌ID
   */
  private Long brandId;

  /**
   * 分类ID
   */
  private Long categoryId;

  /**
   * 店铺id
   */
  private Long shopId;

  /**
   * spu名称
   */
  private String name;

  /**
   * 卖点
   */
  private String sellingPoint;

  /**
   * 主图
   */
  private String mainImgUrl;

  /**
   * 商品图片 多个图片逗号分隔
   */
  private String imgUrls;

  /**
   * 商品视频
   */
  private String video;

  /**
   * 售价，整数方式保存
   */
  private Long priceFee;

  /**
   * 市场价，整数方式保存
   */
  private Long marketPriceFee;

  /**
   * 状态 1:enable, 0:disable, -1:deleted
   */
  private Integer status;

  private Integer hasSkuImg;

  /**
   * 序号
   */
  private Integer seq;
}
