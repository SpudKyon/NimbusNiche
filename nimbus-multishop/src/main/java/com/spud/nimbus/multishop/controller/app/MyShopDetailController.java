package com.spud.nimbus.multishop.controller.app;

import com.spud.nimbus.api.multishop.vo.ShopDetailVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.multishop.dto.ShopDetailDTO;
import com.spud.nimbus.multishop.service.ShopDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/24
 */
@RequestMapping(value = "/my_shop_detail")
@RestController("appMyShopDetailController")
@Tag(name = "app-我的店铺详情信息")
public class MyShopDetailController {

	@Autowired
	private ShopDetailService shopDetailService;

	@PostMapping("/create")
	@Operation(summary = "创建店铺", description = "创建店铺")
	public Result<Void> create(@Valid @RequestBody ShopDetailDTO shopDetailDTO) {
		shopDetailService.createShop(shopDetailDTO);
		return Result.success(null);
	}

	@GetMapping
	@Operation(summary = "获取我的店铺", description = "获取我的店铺")
	public Result<ShopDetailVO> get() {
		Long shopId = AuthUserContext.get().getTenantId();
		if (Objects.isNull(shopId)) {
			return Result.success(null);
		}
		return Result.success(shopDetailService.getByShopId(shopId));
	}

}
