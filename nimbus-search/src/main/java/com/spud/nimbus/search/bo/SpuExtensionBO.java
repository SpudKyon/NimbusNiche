package com.spud.nimbus.search.bo;

import cn.throwx.canal.gule.annotation.CanalModel;
import cn.throwx.canal.gule.common.FieldNamingPolicy;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author spud
 * @date 2024/2/26
 */
@CanalModel(database = "nimbus_product", table = "spu_extension", fieldNamingPolicy = FieldNamingPolicy.LOWER_UNDERSCORE)
@Data
@ToString
public class SpuExtensionBO {
  /**
   * 商品扩展信息表id
   */
  private Long spuExtendId;

  /**
   * 创建时间
   */
  private Date createTime;

  /**
   * 更新时间
   */
  private Date updateTime;

  /**
   * 商品id
   */
  private Long spuId;

  /**
   * 销量
   */
  private Integer saleNum;

  /**
   * 实际库存
   */
  private Integer actualStock;

  /**
   * 锁定库存
   */
  private Integer lockStock;

  /**
   * 可售卖库存
   */
  private Integer stock;
}
