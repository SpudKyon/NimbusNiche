package com.spud.nimbus.api.product.feign;

import com.spud.nimbus.api.product.vo.CategoryVO;
import com.spud.nimbus.common.feign.FeignInsideAuthConfig;
import com.spud.nimbus.common.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/9
 */
@FeignClient(value = "nimbus-product", contextId = "category")
public interface CategoryFeignClient {

	/**
	 * 获取所有一级分类信息
	 * @return 一级分类信息
	 */
	@GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/category/listByOneLevel")
	Result<List<CategoryVO>> listByOneLevel();

	/**
	 * 根据上级id，获取子分类id列表
	 * @param categoryId
	 * @return
	 */
	@GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/category/listCategoryId")
	Result<List<Long>> listCategoryId(@RequestParam("categoryId") Long categoryId);

}