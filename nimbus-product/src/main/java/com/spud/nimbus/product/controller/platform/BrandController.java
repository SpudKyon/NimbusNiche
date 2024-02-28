package com.spud.nimbus.product.controller.platform;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.spud.nimbus.api.product.vo.BrandVO;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.common.exception.NimbusException;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.util.BeanUtil;
import com.spud.nimbus.product.dto.BrandDTO;
import com.spud.nimbus.product.model.Brand;
import com.spud.nimbus.product.service.BrandService;
import com.spud.nimbus.product.service.CategoryBrandService;
import com.spud.nimbus.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
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
@RestController("platformBrandController")
@RequestMapping("v1/platform/brand")
@Tag(name = "platform-品牌信息")
public class BrandController {

	@Autowired
	private BrandService brandService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryBrandService categoryBrandService;

	@GetMapping("/page")
	@Operation(summary = "获取品牌信息列表", description = "分页获取品牌信息列表")
	public Result<PageVO<BrandVO>> page(@Valid PageDTO pageDTO, BrandDTO brandDTO) {
		PageVO<BrandVO> brandPage = brandService.page(pageDTO, brandDTO);
		return Result.success(brandPage);
	}

	@GetMapping
	@Operation(summary = "获取品牌信息", description = "根据brandId获取品牌信息")
	public Result<BrandVO> getByBrandId(@RequestParam Long brandId) {
		BrandVO brand = brandService.getByBrandId(brandId);
		categoryService.getPathNames(brand.getCategories());
		return Result.success(brand);
	}

	@PostMapping
	@Operation(summary = "保存品牌信息", description = "保存品牌信息")
	public Result<Void> save(@Valid @RequestBody BrandDTO brandDTO) {
		if (CollUtil.isEmpty(brandDTO.getCategoryIds())) {
			throw new NimbusException("分类不能为空");
		}
		if (StrUtil.isEmpty(brandDTO.getName())) {
			throw new NimbusException("品牌名称不能为空");
		}
		Brand brand = BeanUtil.map(brandDTO, Brand.class);
		brandService.save(brand, brandDTO.getCategoryIds());
		brandService.removeCache(brandDTO.getCategoryIds());
		return Result.success(null);
	}

	@PutMapping
	@Operation(summary = "更新品牌信息", description = "更新品牌信息")
	public Result<Void> update(@Valid @RequestBody BrandDTO brandDTO) {
		if (CollUtil.isEmpty(brandDTO.getCategoryIds())) {
			throw new NimbusException("分类不能为空");
		}
		Brand brand = BeanUtil.map(brandDTO, Brand.class);
		brandService.update(brand, brandDTO.getCategoryIds());
		// 清楚缓存
		List<Long> categoryIds = categoryBrandService.getCategoryIdBrandId(brand.getBrandId());
		categoryIds.addAll(brandDTO.getCategoryIds());
		brandService.removeCache(categoryIds);
		return Result.success(null);
	}

	@DeleteMapping
	@Operation(summary = "删除品牌信息", description = "根据品牌信息id删除品牌信息")
	public Result<Void> delete(@RequestParam Long brandId) {
		brandService.deleteById(brandId);
		brandService.removeCache(categoryBrandService.getCategoryIdBrandId(brandId));
		return Result.success(null);
	}

	@PutMapping(value = "/update_brand_status")
	@Operation(summary = "更新品牌状态（启用或禁用）", description = "更新品牌状态（启用或禁用）")
	public Result<Void> updateBrandStatus(@RequestBody BrandDTO brandDTO) {
		if (Objects.isNull(brandDTO.getStatus())) {
			throw new NimbusException("状态不能为空");
		}
		if (Objects.isNull(brandDTO.getBrandId())) {
			throw new NimbusException("品牌id不能为空");
		}
		brandService.updateBrandStatus(brandDTO);
		return Result.success(null);
	}

}