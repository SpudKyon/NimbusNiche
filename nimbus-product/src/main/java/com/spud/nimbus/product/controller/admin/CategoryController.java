package com.spud.nimbus.product.controller.admin;

import com.spud.nimbus.api.product.constant.CategoryLevel;
import com.spud.nimbus.api.product.vo.CategoryVO;
import com.spud.nimbus.common.constant.Constant;
import com.spud.nimbus.common.exception.NimbusException;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.common.util.BeanUtil;
import com.spud.nimbus.product.dto.CategoryDTO;
import com.spud.nimbus.product.model.Category;
import com.spud.nimbus.product.service.CategoryAndSpuService;
import com.spud.nimbus.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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
@RestController("platformCategoryController")
@RequestMapping("v1/admin/category")
@Tag(name = "admin-分类信息")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private CategoryAndSpuService categoryAndSpuService;



  @GetMapping
  @Operation(summary = "获取分类信息" , description = "根据categoryId获取分类信息")
  public Result<CategoryVO> getByCategoryId(@RequestParam Long categoryId) {
    return Result.success(categoryService.getById(categoryId));
  }

  @PostMapping
  @Operation(summary = "保存分类信息" , description = "保存分类信息")
  public Result<Void> save(@Valid @RequestBody CategoryDTO categoryDTO) {
    if (!Objects.equals(Constant.PLATFORM_SHOP_ID, AuthUserContext.get().getTenantId()) && categoryDTO.getLevel() > CategoryLevel.SECOND.value()) {
      throw new NimbusException("分类等级最高只能为二级分类");
    }
    Category category = BeanUtil.map(categoryDTO, Category.class);
    categoryService.save(category);
    categoryService.removeCategoryCache(AuthUserContext.get().getTenantId(), category.getParentId());
    return Result.success(null);
  }

  @PutMapping
  @Operation(summary = "更新分类信息" , description = "更新分类信息")
  public Result<Void> update(@Valid @RequestBody CategoryDTO categoryDTO) {
    Category category = BeanUtil.map(categoryDTO, Category.class);
    categoryService.update(category);
    categoryService.removeCategoryCache(AuthUserContext.get().getTenantId(), category.getParentId());
    return Result.success(null);
  }

  @DeleteMapping
  @Operation(summary = "删除分类信息" , description = "根据分类信息id删除分类信息")
  public Result<Void> delete(@RequestParam Long categoryId) {
    CategoryVO categoryVO = categoryService.getById(categoryId);
    categoryService.deleteById(categoryId);
    categoryService.removeCategoryCache(AuthUserContext.get().getTenantId(), categoryVO.getParentId());
    return Result.success(null);
  }

  @GetMapping("/platform_categories")
  @Operation(summary = "获取平台所有的分类信息" , description = "获取所有的分类列表信息")
  public Result<List<CategoryVO>> platformCategories() {
    return Result.success(categoryService.list(Constant.PLATFORM_SHOP_ID));
  }

  @GetMapping("/shop_categories")
  @Operation(summary = "获取店铺所有的分类信息" , description = "获取店铺所有的分类信息")
  public Result<List<CategoryVO>> shopCategories() {
    return Result.success(categoryService.list(AuthUserContext.get().getTenantId()));
  }

  @GetMapping("/get_list_by_parent_id")
  @Operation(summary = "根据上级id，获取分类列表信息" , description = "根据上级id，获取分类列表信息")
  @Parameters(value = {
          @Parameter(name = "parentId", description = "父类id" )
  })
  public Result<List<CategoryVO>> getListByParentId(@RequestParam(value = "parentId") Long parentId) {
    return Result.success(categoryService.listByShopIdAndParenId(parentId, AuthUserContext.get().getTenantId()));
  }

  @PutMapping(value = "/category_enable_or_disable")
  @Operation(summary = "分类的启用或禁用" , description = "分类的启用或禁用")
  public Result<Boolean> categoryEnableOrDisable(@RequestBody CategoryDTO categoryDTO) {
    Boolean isSuccess = categoryAndSpuService.categoryEnableOrDisable(categoryDTO);
    return Result.success(isSuccess);
  }
}