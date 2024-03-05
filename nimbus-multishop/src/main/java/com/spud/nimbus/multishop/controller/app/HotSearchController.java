package com.spud.nimbus.multishop.controller.app;

import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.multishop.service.HotSearchService;
import com.spud.nimbus.multishop.vo.HotSearchVO;
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
 * @date 2024/2/24
 */
@RestController("appHotSearchController")
@RequestMapping("/ua/app/hot_search")
@Tag(name = "app-热搜")
public class HotSearchController {

	private final HotSearchService hotSearchService;

	@Autowired
	public HotSearchController(HotSearchService hotSearchService) {
		this.hotSearchService = hotSearchService;
	}

	@GetMapping("/list")
	@Operation(summary = "获取热搜列表", description = "获取热搜列表")
	@Parameter(name = "shopId", description = "店铺id")
	public Result<List<HotSearchVO>> listByShopId(@RequestParam("shopId") Long shopId) {
		List<HotSearchVO> hotSearches = hotSearchService.listByShopId(shopId);
		return Result.success(hotSearches);
	}

}
