package com.spud.nimbus.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spud.nimbus.product.model.CategoryBrand;

import java.util.List;

/**
 * <p>
 * 品牌分类关联信息 服务类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
public interface CategoryBrandService extends IService<CategoryBrand> {
  /**
   * 根据品牌id删除品牌分类关联信息
   * @param brandId
   */
  void deleteByBrandId(Long brandId);

  /**
   * 保存品牌信息
   * @param brandId
   * @param categoryIds
   */
  void saveByCategoryIds(Long brandId, List<Long> categoryIds);

  /**
   * 更新品牌信息
   * @param brandId
   * @param categoryIds
   */
  void updateByCategoryIds(Long brandId, List<Long> categoryIds);

  /**
   * 根据品牌id或者关联的分类列表
   * @param brandId
   * @return
   */
  List<Long> getCategoryIdBrandId(Long brandId);
}