package com.spud.nimbus.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spud.nimbus.api.product.vo.SpuAttrValueVO;
import com.spud.nimbus.product.model.SpuAttrValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品规格属性关联信息 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Mapper
public interface SpuAttrValueMapper extends BaseMapper<SpuAttrValue> {
  /**
   * 保存商品规格属性关联信息
   *
   * @param spuAttrValue 商品规格属性关联信息
   */
  void save(@Param("spuAttrValue") SpuAttrValue spuAttrValue);

  /**
   * 更新商品规格属性关联信息
   *
   * @param spuAttrValue 商品规格属性关联信息
   */
  void update(@Param("spuAttrValue") SpuAttrValue spuAttrValue);

  /**
   * 批量保存
   *
   * @param spuAttrValues 规格list
   */
  void saveBatch(@Param("spuAttrValues") List<SpuAttrValue> spuAttrValues);

  /**
   * 根据spuId删除对应规格属性
   *
   * @param spuId id
   */
  void deleteBySpuId(Long spuId);

  /**
   * 根据属性和分类id列表删除商品属性关联信息
   *
   * @param attrId       属性id
   * @param attrValueIds 属性值id
   * @param categoryIds  分类id列表
   */
  void deleteByAttIdAndCategoryIds(@Param("attrId") Long attrId, @Param("attrValueIds") List<Long> attrValueIds, @Param("categoryIds") List<Long> categoryIds);

  /**
   * 根据spuId获取商品属性列表
   *
   * @param spuId
   * @return
   */
  List<SpuAttrValueVO> getSpuAttrsBySpuId(@Param("spuId") Long spuId);

  /**
   * 批量更新商品属性列表
   *
   * @param spuAttrValues
   */
  void updateBatch(@Param("spuAttrValues") List<SpuAttrValue> spuAttrValues);

  /**
   * 批量删除商品属性
   *
   * @param spuAttrValueIds
   */
  void deleteBatch(@Param("spuAttrValueIds") List<Long> spuAttrValueIds);

  /**
   * 根据属性值id，获取spuId列表
   *
   * @param attrValueIds
   * @return
   */
  List<Long> getShopIdByAttrValueIds(@Param("attrValueIds") List<Long> attrValueIds);

  /**
   * 批量更新商品基本属性
   *
   * @param spuAttrValues
   */
  void batchUpdateSpuAttrValue(@Param("spuAttrValues") List<SpuAttrValue> spuAttrValues);
}