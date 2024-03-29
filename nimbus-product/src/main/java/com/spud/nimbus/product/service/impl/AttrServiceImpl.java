package com.spud.nimbus.product.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.api.product.vo.AttrVO;
import com.spud.nimbus.api.product.vo.CategoryVO;
import com.spud.nimbus.common.cache.constant.CacheNames;
import com.spud.nimbus.common.cache.util.RedisUtil;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.util.PageAdapter;
import com.spud.nimbus.common.database.util.PageUtil;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.common.exception.NimbusException;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.product.constant.AttrType;
import com.spud.nimbus.product.dto.AttrDTO;
import com.spud.nimbus.product.mapper.AttrMapper;
import com.spud.nimbus.product.model.Attr;
import com.spud.nimbus.product.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 属性信息 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr> implements AttrService {

	private final AttrMapper attrMapper;

	private final AttrCategoryService attrCategoryService;

	private final AttrValueService attrValueService;

	private final CategoryService categoryService;

	private final SpuAttrValueService spuAttrValueService;

	@Autowired
	public AttrServiceImpl(AttrMapper attrMapper, AttrCategoryService attrCategoryService, AttrValueService attrValueService, CategoryService categoryService, SpuAttrValueService spuAttrValueService) {
		this.attrMapper = attrMapper;
		this.attrCategoryService = attrCategoryService;
		this.attrValueService = attrValueService;
		this.categoryService = categoryService;
		this.spuAttrValueService = spuAttrValueService;
	}

	@Override
	public PageVO<AttrVO> page(PageDTO pageDTO, AttrDTO attrDTO) {
		PageVO<AttrVO> pageVO = new PageVO<>();
		attrDTO.setShopId(AuthUserContext.get().getTenantId());
		pageVO.setList(attrMapper.list(new PageAdapter(pageDTO), attrDTO));
		pageVO.setTotal(attrMapper.countAttr(attrDTO));
		pageVO.setPages(PageUtil.getPages(pageVO.getTotal(), pageDTO.getPageSize()));
		return pageVO;
	}

	@Override
	public AttrVO getByAttrId(Long attrId) {
		AttrVO attrVO = attrMapper.getByAttrId(attrId);
		if (Objects.isNull(attrVO)) {
			throw new NimbusException("属性不存在或已删除");
		}
		if (Objects.equals(attrVO.getAttrType(), AttrType.BASIC.value())) {
			attrVO.setCategories(attrCategoryService.listByAttrId(attrId));
			categoryService.getPathNames(attrVO.getCategories());
		}
		return attrVO;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(Attr attr, List<Long> categoryIds) {
		attr.setShopId(AuthUserContext.get().getTenantId());
		attrMapper.save(attr);
		// 保存属性值
		attrValueService.saveByAttrValuesAndAttrId(attr.getAttrValues(), attr.getAttrId());
		// 基本属性关联分类
		if (Objects.equals(AttrType.BASIC.value(), attr.getAttrType())) {
			// 保存属性分类关联信息
			attrCategoryService.save(attr.getAttrId(), categoryIds);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(Attr attr, List<Long> categoryIds) {
		AttrVO dbAttr = attrMapper.getByAttrId(attr.getAttrId());
		// 属性名、描述相等，则设为null，不进行修改操作
		if (Objects.equals(attr.getName(), dbAttr.getName())) {
			attr.setName(null);
		}
		if (Objects.equals(attr.getDesc(), dbAttr.getDesc())) {
			attr.setDesc(null);
		}
		// 保存属性值
		attrValueService.update(attr, dbAttr);
		attrMapper.update(attr);
		if (Objects.equals(dbAttr.getAttrType(), AttrType.BASIC.value())) {
			// 更新属性分类关联信息
			List<Long> ids = attrCategoryService.update(attr.getAttrId(), categoryIds);
			// 清除取消关联的分类的数据
			spuAttrValueService.deleteByAttIdAndCategoryIds(attr.getAttrId(), null, ids);
		}
	}

	@Override
	public void deleteById(Long attrId) {
		AttrVO dbAttr = getByAttrId(attrId);
		if (Objects.isNull(dbAttr)) {
			throw new NimbusException("该属性已删除或不存在");
		}
		if (Objects.equals(dbAttr.getAttrType(), AttrType.BASIC.value())) {
			List<Long> categoryIds = dbAttr.getCategories().stream().map(CategoryVO::getCategoryId)
					.collect(Collectors.toList());
			spuAttrValueService.deleteByAttIdAndCategoryIds(attrId, null, categoryIds);
		}
		attrMapper.deleteById(attrId);
	}

	@Override
	@Cacheable(cacheNames = CacheNames.ATTRS_BY_CATEGORY_KEY, key = "#categoryId")
	public List<AttrVO> getAttrsByCategoryIdAndAttrType(Long categoryId) {
		return attrMapper.getAttrsByCategoryIdAndAttrType(categoryId);
	}

	@Override
	public List<Long> getAttrOfCategoryIdByAttrId(Long attrId) {
		AttrVO attr = attrMapper.getByAttrId(attrId);
		if (Objects.isNull(attr)) {
			throw new NimbusException("属性不存在");
		}
		if (CollUtil.isEmpty(attr.getCategories())) {
			return new ArrayList<>();
		}
		return attr.getCategories().stream().map(CategoryVO::getCategoryId).collect(Collectors.toList());
	}

	@Override
	public void removeAttrByCategoryId(List<Long> categoryIds) {
		if (CollUtil.isEmpty(categoryIds)) {
			return;
		}
		List<String> keys = new ArrayList<>();
		for (Long categoryId : categoryIds) {
			keys.add(CacheNames.ATTRS_BY_CATEGORY_KEY + CacheNames.UNION + categoryId);
		}
		RedisUtil.deleteBatch(keys);
	}

	@Override
	public List<AttrVO> getShopAttrs(Long shopId) {
		return attrMapper.getShopAttrs(shopId);
	}

}