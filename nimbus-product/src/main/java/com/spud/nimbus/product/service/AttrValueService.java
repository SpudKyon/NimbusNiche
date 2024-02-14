package com.spud.nimbus.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spud.nimbus.api.product.vo.AttrVO;
import com.spud.nimbus.product.model.Attr;
import com.spud.nimbus.product.model.AttrValue;

import java.util.List;

/**
 * <p>
 * 属性值信息 服务类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
public interface AttrValueService extends IService<AttrValue> {

  /**
   * 根据属性值信息和属性id，保存属性值信息
   *
   * @param attrValues
   * @param attrId
   */
  void saveByAttrValuesAndAttrId(List<AttrValue> attrValues, Long attrId);

  /**
   * 根据属性值信息和属性id，更新属性值信息
   *
   * @param attrVO
   * @param dbAttr
   */
  void update(Attr attrVO, AttrVO dbAttr);
}
