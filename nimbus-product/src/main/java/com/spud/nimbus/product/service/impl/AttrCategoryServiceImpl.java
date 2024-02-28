package com.spud.nimbus.product.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.api.product.vo.CategoryVO;
import com.spud.nimbus.product.mapper.AttrCategoryMapper;
import com.spud.nimbus.product.model.AttrCategory;
import com.spud.nimbus.product.service.AttrCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 属性与分类关联信息 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class AttrCategoryServiceImpl extends ServiceImpl<AttrCategoryMapper, AttrCategory>
		implements AttrCategoryService {

	@Autowired
	private AttrCategoryMapper attrCategoryMapper;

	@Override
	public void save(Long attrId, List<Long> categoryId) {
		attrCategoryMapper.saveBatch(attrId, categoryId);
	}

	@Override
	public List<Long> update(Long attrId, List<Long> categoryIds) {
		if (CollUtil.isEmpty(categoryIds)) {
			return new ArrayList<>();
		}
		List<Long> dbCategoryIds = attrCategoryMapper.getCategoryIdsByAttrId(attrId);
		List<Long> addList = new ArrayList<>(categoryIds.size());
		addList.addAll(categoryIds);
		addList.removeAll(dbCategoryIds);
		if (CollUtil.isNotEmpty(addList)) {
			attrCategoryMapper.saveBatch(attrId, addList);
		}
		dbCategoryIds.removeAll(categoryIds);
		if (CollUtil.isNotEmpty(dbCategoryIds)) {
			attrCategoryMapper.deleteBatch(attrId, dbCategoryIds);
		}
		return dbCategoryIds;
	}

	@Override
	public List<CategoryVO> listByAttrId(Long attrId) {
		return attrCategoryMapper.listByAttrId(attrId);
	}

}