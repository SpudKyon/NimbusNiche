package com.spud.nimbus.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spud.nimbus.product.model.SpuDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品详情信息 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Mapper
public interface SpuDetailMapper extends BaseMapper<SpuDetail> {

	/**
	 * 保存商品详情信息
	 * @param spuDetail 商品详情信息
	 */
	void save(@Param("spuDetail") SpuDetail spuDetail);

	/**
	 * 更新商品详情信息
	 * @param spuDetail 商品详情信息
	 */
	void update(@Param("spuDetail") SpuDetail spuDetail);

	/**
	 * 根据商品详情信息id删除商品详情信息
	 * @param spuId
	 */
	void deleteById(@Param("spuId") Long spuId);

}
