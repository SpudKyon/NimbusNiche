package com.spud.nimbus.search.bo;

import cn.throwx.canal.gule.annotation.CanalModel;
import cn.throwx.canal.gule.common.FieldNamingPolicy;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/26
 */
@CanalModel(database = "nimbus_multishop", table = "shop_detail", fieldNamingPolicy = FieldNamingPolicy.LOWER_UNDERSCORE)
@Data
@ToString
public class ShopDetailBO {
  /**
   * 店铺id
   */
  private Long shopId;

  /**
   * 店铺类型1自营店 2普通店
   */
  private Integer type;

  /**
   * 店铺名称
   */
  private String shopName;

  /**
   * 店铺logo(可修改)
   */
  private String shopLogo;

  /**
   * 店铺状态(-1:未开通 0: 停业中 1:营业中 2:平台下线 3:平台下线待审核)
   */
  private Integer shopStatus;
}
