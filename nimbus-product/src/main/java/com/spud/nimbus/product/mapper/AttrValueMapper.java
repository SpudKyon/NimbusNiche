package com.spud.nimbus.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spud.nimbus.product.model.AttrValue;
import com.spud.nimbus.product.model.SpuAttrValue;
import com.spud.nimbus.product.model.SpuSkuAttrValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 属性值信息 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Mapper
public interface AttrValueMapper extends BaseMapper<AttrValue> {
  /**
   * 批量保存属性值
   *
   * @param attrValues
   */
  void saveBatch(@Param("attrValues") List<AttrValue> attrValues);

  /**
   * 根据 attrId 获取属性值id列表
   *
   * @param attrId
   * @return
   */
  List<Long> getIdListByAttrId(@Param("attrId") Long attrId);

  /**
   * 批量更新
   *
   * @param attrValues
   */
  void updateBatch(@Param("attrValues") List<AttrValue> attrValues);

  /**
   * 批量删除
   *
   * @param attrValueIds
   */
  void deleteBatch(@Param("attrValueIds") List<Long> attrValueIds);

  /**
   * 批量更新spu中的规格数据
   *
   * @param spuAttrValues
   */
  void updateBatchOfSpuAttrValue(@Param("spuAttrValues") List<SpuAttrValue> spuAttrValues);

  /**
   * 批量更新sku中的规格数据
   *
   * @param spuSkuAttrValues
   */
  void updateBatchOfSpuSkuAttrValue(@Param("spuSkuAttrValues") List<SpuSkuAttrValue> spuSkuAttrValues);
}
