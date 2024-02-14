package com.spud.nimbus.product.controller.admin;

import com.spud.nimbus.api.product.vo.BrandVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.product.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/13
 */
@RestController("adminBrandController")
@RequestMapping("v1/admin/brand")
@Tag(name = "admin-品牌信息")
public class BrandController {

  @Autowired
  private BrandService brandService;

  @GetMapping("/get_brand_by_category_id")
  @Parameter(name = "categoryId", description = "分类id" )
  @Operation(summary = "根据分类，获取品牌列表" , description = "根据分类，获取品牌列表")
  public Result<List<BrandVO>> getBrandByCategoryId(@RequestParam Long categoryId) {
    return Result.success(brandService.getBrandByCategoryId(categoryId));
  }
}