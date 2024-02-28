package com.spud.nimbus.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spud.nimbus.api.product.dto.SkuStockLockDTO;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.product.model.SkuStockLock;

import java.util.List;

/**
 * <p>
 * 库存锁定信息 服务类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
public interface SkuStockLockService extends IService<SkuStockLock> {

	/**
	 * 分页获取库存锁定信息列表
	 * @param pageDTO 分页参数
	 * @return 库存锁定信息列表分页数据
	 */
	PageVO<SkuStockLock> page(PageDTO pageDTO);

	/**
	 * 根据库存锁定信息id获取库存锁定信息
	 * @param id 库存锁定信息id
	 * @return 库存锁定信息
	 */
	SkuStockLock getById(Long id);

	/**
	 * 保存库存锁定信息
	 * @param skuStockLock 库存锁定信息
	 */
	boolean save(SkuStockLock skuStockLock);

	/**
	 * 更新库存锁定信息
	 * @param skuStockLock 库存锁定信息
	 */
	void update(SkuStockLock skuStockLock);

	/**
	 * 根据库存锁定信息id删除库存锁定信息
	 * @param id 库存锁定信息id
	 */
	void deleteById(Long id);

	/**
	 * 锁定库存
	 * @param skuStockLocksParam 参数
	 * @return 是否成功
	 */
	Result<Void> lock(List<SkuStockLockDTO> skuStockLocksParam);

	/**
	 * 根据订单号进行库存解锁
	 * @param orderIds 订单ids
	 */
	void unlockStock(List<Long> orderIds);

	/**
	 * 正式锁定库存，标记为使用状态
	 * @param orderIds 订单ids
	 */
	void markerStockUse(List<Long> orderIds);

}
