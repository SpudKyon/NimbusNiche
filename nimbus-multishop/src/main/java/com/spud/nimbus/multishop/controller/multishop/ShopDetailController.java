package com.spud.nimbus.multishop.controller.multishop;

import com.spud.nimbus.api.multishop.vo.ShopDetailVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.multishop.service.ShopDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author spud
 * @date 2024/2/24
 */
@RequestMapping(value = "/m/shop_detail")
@RestController("multishopShopDetailController")
@Tag(name = "multishop-店铺详情信息")
public class ShopDetailController {

	@Autowired
	private ShopDetailService shopDetailService;

	@GetMapping("/info")
	@Operation(summary = "获取店铺详情信息", description = "获取店铺详情信息")
	public Result<ShopDetailVO> info() {
		Long shopId = AuthUserContext.get().getTenantId();
		ShopDetailVO shopDetailVO = shopDetailService.getByShopId(shopId);
		return Result.success(shopDetailVO);
	}

}
