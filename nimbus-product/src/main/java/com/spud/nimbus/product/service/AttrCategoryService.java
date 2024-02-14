package com.spud.nimbus.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spud.nimbus.api.product.vo.CategoryVO;
import com.spud.nimbus.product.model.AttrCategory;

import java.util.List;

/**
 * <p>
 * 属性与分类关联信息 服务类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
public interface AttrCategoryService extends IService<AttrCategory> {
  /**
   * 保存属性与属性分组关联信息
   * @param attrId 属性id
   * @param categoryId 分类id列表
   */
  void save(Long attrId, List<Long> categoryId);

  /**
   * 更新属性与属性分组关联信息
   * @param attrId
   * @param categoryId
   * @return
   */
  List<Long> update(Long attrId, List<Long> categoryId);

  /**
   * 根据属性Id，获取属性关联的分类列表信息
   * @param attrId
   * @return
   */
  List<CategoryVO> listByAttrId(Long attrId);
}
