package com.spud.nimbus.multishop.controller.app;

import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.multishop.service.IndexImgService;
import com.spud.nimbus.multishop.vo.IndexImgVO;
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
@RestController("appIndexImgController")
@RequestMapping("/ua/index_img")
@Tag(name = "app-轮播图")
public class IndexImgController {

	@Autowired
	private IndexImgService indexImgService;

	@GetMapping("/list")
	@Operation(summary = "获取轮播图列表", description = "分页获取轮播图列表")
	@Parameter(name = "shopId", description = "店铺id（平台：0）")
	public Result<List<IndexImgVO>> getList(@RequestParam("shopId") Long shopId) {
		List<IndexImgVO> indexImgPage = indexImgService.getListByShopId(shopId);
		return Result.success(indexImgPage);
	}

}
