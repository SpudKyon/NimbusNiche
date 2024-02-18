package com.spud.nimbus.product.model;

import com.spud.nimbus.common.model.BaseModel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author spud
 * @date 2024/2/10
 */
@Data
@ToString
public class SpuSkuAttrValue extends BaseModel implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 商品sku销售属性关联信息id
   */
  private Long spuSkuAttrId;

  /**
   * SPU ID
   */
  private Long spuId;

  /**
   * SKU ID
   */
  private Long skuId;

  /**
   * 销售属性ID
   */
  private Long attrId;

  /**
   * 销售属性名称
   */
  private String attrName;

  /**
   * 销售属性值ID
   */
  private Long attrValueId;

  /**
   * 销售属性值
   */
  private String attrValueName;

  /**
   * 状态 1:enable, 0:disable, -1:deleted
   */
  private Integer status;
}
