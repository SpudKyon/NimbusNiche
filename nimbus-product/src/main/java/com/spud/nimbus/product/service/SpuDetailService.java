package com.spud.nimbus.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spud.nimbus.product.model.SpuDetail;

/**
 * <p>
 * 商品详情信息 服务类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
public interface SpuDetailService extends IService<SpuDetail> {

	/**
	 * 保存商品详情信息
	 * @param spuDetail 商品详情信息
	 */
	boolean save(SpuDetail spuDetail);

	/**
	 * 更新商品详情信息
	 * @param spuDetail 商品详情信息
	 */
	void update(SpuDetail spuDetail);

	/**
	 * 根据商品详情信息id删除商品详情信息
	 * @param spuId
	 */
	void deleteById(Long spuId);

}
