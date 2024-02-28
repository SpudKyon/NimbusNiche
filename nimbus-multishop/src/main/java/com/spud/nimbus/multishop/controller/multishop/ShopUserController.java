package com.spud.nimbus.multishop.controller.multishop;

import com.spud.nimbus.api.auth.bo.UserInfoInTokenBO;
import com.spud.nimbus.api.multishop.vo.ShopDetailVO;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.response.ResultCode;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.common.util.BeanUtil;
import com.spud.nimbus.multishop.dto.ShopUserDTO;
import com.spud.nimbus.multishop.model.ShopUser;
import com.spud.nimbus.multishop.service.ShopDetailService;
import com.spud.nimbus.multishop.service.ShopUserService;
import com.spud.nimbus.multishop.vo.ShopUserSimpleVO;
import com.spud.nimbus.multishop.vo.ShopUserVO;
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
@RequestMapping(value = "/m/shop_user")
@RestController("multishopShopUserController")
@Tag(name = "店铺用户信息")
public class ShopUserController {

	@Autowired
	private ShopUserService shopUserService;

	@Autowired
	private ShopDetailService shopDetailService;

	@GetMapping("/info")
	@Operation(summary = "登陆店铺用户信息", description = "获取当前登陆店铺用户的用户信息")
	public Result<ShopUserSimpleVO> info() {
		UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
		ShopUserSimpleVO shopUserSimple = new ShopUserSimpleVO();
		shopUserSimple.setIsAdmin(userInfoInTokenBO.getIsAdmin());
		ShopDetailVO shopDetail = shopDetailService.getByShopId(userInfoInTokenBO.getTenantId());
		shopUserSimple.setAvatar(shopDetail.getShopLogo());
		shopUserSimple.setNickName(shopDetail.getShopName());
		return Result.success(shopUserSimple);
	}

	@GetMapping("/page")
	@Operation(summary = "店铺用户列表", description = "获取店铺用户列表")
	public Result<PageVO<ShopUserVO>> page(@Valid PageDTO pageDTO, String nickName) {
		UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
		PageVO<ShopUserVO> shopUserPage = shopUserService.pageByShopId(pageDTO, userInfoInTokenBO.getTenantId(),
				nickName);
		return Result.success(shopUserPage);
	}

	@GetMapping
	@Operation(summary = "获取店铺用户信息", description = "根据用户id获取店铺用户信息")
	public Result<ShopUserVO> detail(@RequestParam Long shopUserId) {
		return Result.success(shopUserService.getByUserId(shopUserId));
	}

	@PostMapping
	@Operation(summary = "保存店铺用户信息", description = "保存店铺用户信息")
	public Result<Void> save(@Valid @RequestBody ShopUserDTO shopUserDTO) {
		ShopUser shopUser = BeanUtil.map(shopUserDTO, ShopUser.class);
		shopUser.setShopUserId(null);
		shopUser.setShopId(AuthUserContext.get().getTenantId());
		shopUser.setHasAccount(0);
		shopUserService.save(shopUser, shopUserDTO.getRoleIds());
		return Result.success(null);
	}

	@PutMapping
	@Operation(summary = "更新店铺用户信息", description = "更新店铺用户信息")
	public Result<Void> update(@Valid @RequestBody ShopUserDTO shopUserDTO) {
		ShopUser shopUser = BeanUtil.map(shopUserDTO, ShopUser.class);
		ShopUserVO dbShopUser = shopUserService.getByUserId(shopUserDTO.getShopUserId());
		if (!Objects.equals(dbShopUser.getShopId(), AuthUserContext.get().getTenantId())) {
			return Result.fail(ResultCode.UNAUTHORIZED, null);
		}
		shopUser.setShopId(dbShopUser.getShopId());
		shopUserService.update(shopUser, shopUserDTO.getRoleIds());
		return Result.success(null);
	}

	@DeleteMapping
	@Operation(summary = "删除店铺用户信息", description = "根据店铺用户id删除店铺用户信息")
	public Result<Void> delete(@RequestParam Long shopUserId) {
		ShopUserVO dbShopUser = shopUserService.getByUserId(shopUserId);
		if (!Objects.equals(dbShopUser.getShopId(), AuthUserContext.get().getTenantId())) {
			return Result.fail(ResultCode.UNAUTHORIZED, null);
		}
		shopUserService.deleteById(shopUserId);
		return Result.success(null);
	}

}
