package com.spud.nimbus.search.bo;

import cn.throwx.canal.gule.annotation.CanalModel;
import cn.throwx.canal.gule.common.FieldNamingPolicy;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/26
 */
@CanalModel(database = "nimbus_product", table = "brand", fieldNamingPolicy = FieldNamingPolicy.LOWER_UNDERSCORE)
@Data
@ToString
public class BrandBO {
  /**
   * brand_id
   */
  private Long brandId;

  /**
   * 品牌名称
   */
  private String name;

  /**
   * 品牌描述
   */
  private String desc;

  /**
   * 品牌logo图片
   */
  private String imgUrl;
}