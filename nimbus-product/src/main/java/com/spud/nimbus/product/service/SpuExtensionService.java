package com.spud.nimbus.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.product.model.SpuExtension;
import com.spud.nimbus.product.vo.SpuExtensionVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
public interface SpuExtensionService extends IService<SpuExtension> {

	/**
	 * 分页获取列表
	 * @param pageDTO 分页参数
	 * @return 列表分页数据
	 */
	PageVO<SpuExtensionVO> page(PageDTO pageDTO);

	/**
	 * 根据id获取
	 * @param spuExtendId id
	 * @return
	 */
	SpuExtensionVO getBySpuExtendId(Long spuExtendId);

	/**
	 * 保存
	 * @param spuExtension 商品扩展信息
	 */
	boolean save(SpuExtension spuExtension);

	/**
	 * 更新库存
	 * @param spuId 商品id
	 * @param count 商品数量
	 */
	void updateStock(Long spuId, Integer count);

	/**
	 * 根据id删除
	 * @param spuId
	 */
	void deleteById(Long spuId);

	/**
	 * 获取spu扩展信息
	 * @param spuId
	 * @return
	 */
	SpuExtension getBySpuId(Long spuId);

}
