package com.spud.nimbus.product.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.product.mapper.CategoryBrandMapper;
import com.spud.nimbus.product.model.CategoryBrand;
import com.spud.nimbus.product.service.CategoryBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 品牌分类关联信息 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class CategoryBrandServiceImpl extends ServiceImpl<CategoryBrandMapper, CategoryBrand>
		implements CategoryBrandService {

	private final CategoryBrandMapper categoryBrandMapper;

	@Autowired
	public CategoryBrandServiceImpl(CategoryBrandMapper categoryBrandMapper) {
		this.categoryBrandMapper = categoryBrandMapper;
	}

	@Override
	public void deleteByBrandId(Long brandId) {
		categoryBrandMapper.deleteByBrandId(brandId);
	}

	@Override
	public void saveByCategoryIds(Long brandId, List<Long> categoryIds) {
		if (CollUtil.isEmpty(categoryIds)) {
			return;
		}
		List<CategoryBrand> categoryBrandList = new ArrayList<>();
		categoryIds.forEach(categoryId -> {
			CategoryBrand categoryBrand = new CategoryBrand();
			categoryBrand.setBrandId(brandId);
			categoryBrand.setCategoryId(categoryId);
			categoryBrandList.add(categoryBrand);
		});
		categoryBrandMapper.saveBatch(categoryBrandList);
	}

	@Override
	public void updateByCategoryIds(Long brandId, List<Long> categoryIds) {
		if (CollUtil.isEmpty(categoryIds)) {
			return;
		}
		List<Long> categoryIdDb = getCategoryIdBrandId(brandId);
		List<Long> addList = new ArrayList<>();
		categoryIds.forEach(categoryId -> {
			if (!categoryIdDb.contains(categoryId)) {
				addList.add(categoryId);
			}
		});
		if (CollUtil.isNotEmpty(addList)) {
			saveByCategoryIds(brandId, addList);
		}
		categoryIdDb.removeAll(categoryIds);
		if (CollUtil.isNotEmpty(categoryIdDb)) {
			categoryBrandMapper.deleteByBrandIdAndCategoryIds(brandId, categoryIdDb);
		}
	}

	@Override
	public List<Long> getCategoryIdBrandId(Long brandId) {
		return categoryBrandMapper.getCategoryIdsByBrandId(brandId);
	}

}