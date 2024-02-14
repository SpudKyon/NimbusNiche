package com.spud.nimbus.product.feign;

import com.spud.nimbus.api.product.feign.CategoryFeignClient;
import com.spud.nimbus.api.product.vo.CategoryVO;
import com.spud.nimbus.common.constant.Constant;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/10
 */
@RestController
public class CategoryFeignController implements CategoryFeignClient {

  @Autowired
  private CategoryService categoryService;

  @Override
  public Result<List<CategoryVO>> listByOneLevel() {
    return Result.success(categoryService.listByShopIdAndParenId(Constant.PLATFORM_SHOP_ID, Constant.CATEGORY_ID));
  }

  @Override
  public Result<List<Long>> listCategoryId(Long categoryId) {
    CategoryVO category = categoryService.getById(categoryId);
    List<Long> categoryIds = categoryService.listCategoryId(category.getShopId(), category.getParentId());
    return Result.success(categoryIds);
  }
}
