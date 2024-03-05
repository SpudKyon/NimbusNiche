package com.spud.nimbus.product.controller.admin;

import cn.hutool.core.collection.CollUtil;
import com.spud.nimbus.api.product.vo.AttrVO;
import com.spud.nimbus.common.constant.Constant;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.common.exception.NimbusException;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.common.util.BeanUtil;
import com.spud.nimbus.product.constant.AttrType;
import com.spud.nimbus.product.constant.SearchType;
import com.spud.nimbus.product.dto.AttrDTO;
import com.spud.nimbus.product.model.Attr;
import com.spud.nimbus.product.model.AttrValue;
import com.spud.nimbus.product.service.AttrService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/13
 */
@RestController("platformAttrController")
@RequestMapping("v1/admin/attr")
@Tag(name = "admin-属性信息")
public class AttrController {

	private final AttrService attrService;

	@Autowired
	public AttrController(AttrService attrService) {
		this.attrService = attrService;
	}

	@GetMapping("/page")
	@Operation(summary = "获取属性信息列表", description = "分页获取属性信息列表")
	public Result<PageVO<AttrVO>> page(@Valid PageDTO pageDTO, AttrDTO attrDTO) {
		PageVO<AttrVO> attrPage = attrService.page(pageDTO, attrDTO);
		return Result.success(attrPage);
	}

	@GetMapping
	@Operation(summary = "获取属性信息", description = "根据attrId获取属性信息")
	public Result<AttrVO> getByAttrId(@RequestParam Long attrId) {
		return Result.success(attrService.getByAttrId(attrId));
	}

	@PostMapping
	@Operation(summary = "保存属性信息", description = "保存属性信息")
	public Result<Void> save(@Valid @RequestBody AttrDTO attrDTO) {
		if (Objects.equals(Constant.PLATFORM_SHOP_ID, AuthUserContext.get().getTenantId())
				&& Objects.isNull(attrDTO.getAttrType())) {
			throw new NimbusException("属性类型不能为空");
		}
		checkAttrInfo(attrDTO);
		Attr attr = BeanUtil.map(attrDTO, Attr.class);
		attr.setAttrValues(BeanUtil.mapAsList(attrDTO.getAttrValues(), AttrValue.class));
		attrService.save(attr, attrDTO.getCategoryIds());
		removeCacheAttrUnionCategory(attrDTO.getCategoryIds());
		return Result.success(null);
	}

	@PutMapping
	@Operation(summary = "更新属性信息", description = "更新属性信息")
	public Result<Void> update(@Valid @RequestBody AttrDTO attrDTO) {
		checkAttrInfo(attrDTO);
		Attr attr = BeanUtil.map(attrDTO, Attr.class);
		if (CollUtil.isNotEmpty(attrDTO.getAttrValues())) {
			attr.setAttrValues(BeanUtil.mapAsList(attrDTO.getAttrValues(), AttrValue.class));
		}
		List<Long> categoryIds = null;
		if (Objects.equals(AttrType.BASIC.value(), attr.getAttrType())) {
			categoryIds = attrService.getAttrOfCategoryIdByAttrId(attrDTO.getAttrId());
			categoryIds.addAll(attrDTO.getCategoryIds());
		}
		attrService.update(attr, attrDTO.getCategoryIds());
		removeCacheAttrUnionCategory(categoryIds);
		return Result.success(null);
	}

	@DeleteMapping
	@Operation(summary = "删除属性信息", description = "根据属性信息id删除属性信息")
	public Result<Void> delete(@RequestParam Long attrId) {
		List<Long> categoryIds = attrService.getAttrOfCategoryIdByAttrId(attrId);
		attrService.deleteById(attrId);
		if (CollUtil.isNotEmpty(categoryIds)) {
			removeCacheAttrUnionCategory(categoryIds);
		}
		return Result.success(null);
	}

	@GetMapping("/get_attrs_by_category_id")
	@Operation(summary = "根据分类及属性类别获取属性列表", description = "根据分类及属性类别获取属性列表")
	@Parameter(name = "categoryId", description = "分类id", required = true)
	public Result<List<AttrVO>> getAttrsByCategoryId(@RequestParam(value = "categoryId") Long categoryId) {
		return Result.success(attrService.getAttrsByCategoryIdAndAttrType(categoryId));
	}

	@GetMapping("/get_shop_attrs")
	@Operation(summary = "获取店铺中的销售属性", description = "获取店铺中的销售属性")
	public Result<List<AttrVO>> getShopAttrs() {
		return Result.success(attrService.getShopAttrs(AuthUserContext.get().getTenantId()));
	}

	/**
	 * 校验属性数据
	 * @param attrDTO
	 */
	private void checkAttrInfo(AttrDTO attrDTO) {
		if (!Objects.equals(Constant.PLATFORM_SHOP_ID, AuthUserContext.get().getTenantId())) {
			attrDTO.setAttrType(AttrType.SALES.value());
		}
		if (Objects.equals(AttrType.SALES.value(), attrDTO.getAttrType())) {
			attrDTO.setSearchType(SearchType.NOT_SEARCH.value());
			return;
		}
		if (CollUtil.isEmpty(attrDTO.getCategoryIds())) {
			throw new NimbusException("关联分类不能为空");
		}
		if (Objects.isNull(attrDTO.getSearchType())) {
			throw new NimbusException("搜索属性不能为空");
		}
	}

	/**
	 * 删除属性关联的分类缓存
	 */
	private void removeCacheAttrUnionCategory(List<Long> categoryIds) {
		// 清除分类缓存
		if (!Objects.equals(Constant.PLATFORM_SHOP_ID, AuthUserContext.get().getTenantId())
				|| CollUtil.isEmpty(categoryIds)) {
			return;
		}
		attrService.removeAttrByCategoryId(categoryIds);
	}

}