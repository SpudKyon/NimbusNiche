package com.spud.nimbus.product.controller.app;

import com.spud.nimbus.api.product.vo.BrandVO;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.product.dto.BrandDTO;
import com.spud.nimbus.product.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/13
 */
@RestController("appBrandController")
@RequestMapping("v1/ua/brand")
@Tag(name = "app-品牌信息")
public class BrandController {

	@Autowired
	private BrandService brandService;

	@GetMapping("/page")
	@Operation(summary = "获取品牌信息列表", description = "分页获取品牌信息列表")
	public Result<PageVO<BrandVO>> page(@Valid PageDTO pageDTO, BrandDTO brandDTO) {
		PageVO<BrandVO> brandPage = brandService.page(pageDTO, brandDTO);
		return Result.success(brandPage);
	}

	@GetMapping("/top_brand_list")
	@Operation(summary = "置顶品牌列表", description = "置顶品牌列表")
	public Result<List<BrandVO>> topBrandList() {
		List<BrandVO> brandPage = brandService.topBrandList();
		return Result.success(brandPage);
	}

	@GetMapping("/list_by_category")
	@Operation(summary = "分类-推荐品牌信息列表", description = "分类-推荐品牌信息列表")
	public Result<List<BrandVO>> getTopBrandList(Long categoryId) {
		List<BrandVO> brandPage = brandService.listByCategory(categoryId);
		return Result.success(brandPage);
	}

}
