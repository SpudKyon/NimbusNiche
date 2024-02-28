package com.spud.nimbus.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spud.nimbus.api.product.vo.SpuAttrValueVO;
import com.spud.nimbus.product.model.SpuAttrValue;

import java.util.List;

/**
 * <p>
 * 商品规格属性关联信息 服务类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
public interface SpuAttrValueService extends IService<SpuAttrValue> {

	/**
	 * 更新商品规格属性关联信息
	 * @param spuId id
	 * @param spuAttrValues 商品属性信息
	 * @param spuAttrValuesDb 缓存中的商品属性信息
	 */
	void update(Long spuId, List<SpuAttrValue> spuAttrValues, List<SpuAttrValueVO> spuAttrValuesDb);

	/**
	 * 批量保存
	 * @param spuId id
	 * @param spuAttrValues 规格属性信息
	 */
	void saveBatch(Long spuId, List<SpuAttrValue> spuAttrValues);

	/**
	 * 根据商品id删除spuId
	 * @param spuId id
	 */
	void deleteBySpuId(Long spuId);

	/**
	 * 根据属性和分类id列表删除商品属性关联信息, 并发送消息到队列
	 * @param attrId
	 * @param attrValueId
	 * @param categoryIds
	 */
	void deleteByAttIdAndCategoryIds(Long attrId, List<Long> attrValueId, List<Long> categoryIds);

	/**
	 * 根据spuId获取商品属性列表
	 * @param spuId
	 * @return
	 */
	List<SpuAttrValueVO> getSpuAttrsBySpuId(Long spuId);

	/**
	 * 批量更新商品基本属性
	 * @param spuAttrValues
	 */
	void batchUpdateSpuAttrValue(List<SpuAttrValue> spuAttrValues);

}