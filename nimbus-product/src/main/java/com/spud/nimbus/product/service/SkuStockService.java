package com.spud.nimbus.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spud.nimbus.api.product.vo.SkuVO;
import com.spud.nimbus.product.dto.SkuDTO;
import com.spud.nimbus.product.model.SkuStock;
import com.spud.nimbus.product.vo.SkuStockVO;

import java.util.List;

/**
 * <p>
 * 库存信息 服务类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
public interface SkuStockService extends IService<SkuStock> {

	/**
	 * 保存库存信息
	 * @param skuStock 库存信息
	 */
	boolean save(SkuStock skuStock);

	/**
	 * 更新库存信息
	 * @param skuStock 库存信息
	 */
	void update(SkuStock skuStock);

	/**
	 * 根据库存信息id删除库存信息
	 * @param stockId
	 */
	void deleteById(Long stockId);

	/**
	 * 批量保存库存信息
	 * @param skuStocks 库存信息
	 */
	void saveBatch(List<SkuStock> skuStocks);

	/**
	 * 根据skuIds删除库存信息
	 * @param skuIds ids
	 */
	void deleteBySkuIds(List<Long> skuIds);

	/**
	 * 根据sku列表获取库存信息
	 * @param skuVOList sku列表
	 * @return 库存信息
	 */
	List<SkuStockVO> listBySkuList(List<SkuVO> skuVOList);

	/**
	 * 批量更新sku库存信息
	 * @param skuList
	 */
	void updateBatch(List<SkuDTO> skuList);

}
