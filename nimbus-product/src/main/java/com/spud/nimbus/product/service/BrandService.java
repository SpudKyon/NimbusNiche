package com.spud.nimbus.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spud.nimbus.api.product.vo.BrandVO;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.product.dto.BrandDTO;
import com.spud.nimbus.product.model.Brand;

import java.util.List;

/**
 * <p>
 * 品牌信息 服务类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
public interface BrandService extends IService<Brand> {

	/**
	 * 分页获取品牌信息列表
	 * @param pageDTO 分页参数
	 * @param brandDTO
	 * @return 品牌信息列表分页数据
	 */
	PageVO<BrandVO> page(PageDTO pageDTO, BrandDTO brandDTO);

	/**
	 * 根据品牌信息id获取品牌信息
	 * @param brandId 品牌信息id
	 * @return 品牌信息
	 */
	BrandVO getByBrandId(Long brandId);

	/**
	 * 保存品牌信息
	 * @param brand 品牌信息
	 * @param categoryIds 分类id列表
	 */
	void save(Brand brand, List<Long> categoryIds);

	/**
	 * 更新品牌信息
	 * @param brand 品牌信息
	 * @param categoryIds 分类id列表
	 */
	void update(Brand brand, List<Long> categoryIds);

	/**
	 * 根据品牌id，删除品牌
	 * @param brandId
	 */
	void deleteById(Long brandId);

	/**
	 * 根据分类id，获取品牌数据
	 * @param categoryId
	 * @return
	 */
	List<BrandVO> getBrandByCategoryId(Long categoryId);

	/**
	 * 更新品牌状态（启用或禁用）
	 * @param brandDTO
	 * @return
	 */
	void updateBrandStatus(BrandDTO brandDTO);

	/**
	 * 根据分类id，获取品牌列表
	 * @param categoryId
	 * @return
	 */
	List<BrandVO> listByCategory(Long categoryId);

	/**
	 * 获取置顶品牌列表
	 * @return
	 */
	List<BrandVO> topBrandList();

	/**
	 * 清楚分类缓存
	 * @param categoryIds
	 */
	void removeCache(List<Long> categoryIds);

}
