package com.spud.nimbus.multishop.controller.platform;

import com.spud.nimbus.api.multishop.vo.ShopDetailVO;
import com.spud.nimbus.common.constant.Constant;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.common.exception.NimbusException;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.response.ResultCode;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.common.util.BeanUtil;
import com.spud.nimbus.multishop.dto.ShopDetailDTO;
import com.spud.nimbus.multishop.model.ShopDetail;
import com.spud.nimbus.multishop.service.ShopDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/24
 */
@RestController("platformShopDetailController")
@RequestMapping("/platform/shop_detail")
@Tag(name = "platform-店铺信息")
public class ShopDetailController {

	@Autowired
	private ShopDetailService shopDetailService;

	@GetMapping("/page")
	@Operation(summary = "分页查询", description = "分页查询")
	public Result<PageVO<ShopDetailVO>> getShopAuditingPage(PageDTO pageDTO, ShopDetailDTO shopDetailDTO) {
		if (!Objects.equals(Constant.PLATFORM_SHOP_ID, AuthUserContext.get().getTenantId())) {
			throw new NimbusException(ResultCode.UNAUTHORIZED);
		}
		return Result.success(shopDetailService.page(pageDTO, shopDetailDTO));
	}

	@GetMapping("/info")
	@Operation(summary = "店铺详情", description = "店铺详情")
	public Result<ShopDetailVO> getInfo(@RequestParam Long shopId) {
		ShopDetailVO shopDetailVO = shopDetailService.getByShopId(shopId);
		return Result.success(shopDetailVO);
	}

	/**
	 * 新建店铺
	 */
	@PostMapping("/create_shop")
	@Operation(summary = "新建店铺", description = "新建店铺")
	public Result<Void> createShop(@RequestBody ShopDetailDTO shopDetailDTO) {
		shopDetailService.createShop(shopDetailDTO);
		return Result.success(null);
	}

	@PutMapping("/update_shop")
	@Operation(summary = "更新店铺", description = "更新店铺")
	public Result<Void> updateShop(@RequestBody ShopDetailDTO shopDetailDTO) {
		shopDetailService.update(BeanUtil.map(shopDetailDTO, ShopDetail.class));
		return Result.success(null);
	}

}
