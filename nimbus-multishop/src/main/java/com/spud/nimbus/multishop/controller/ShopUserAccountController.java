package com.spud.nimbus.multishop.controller;

import com.spud.nimbus.api.auth.vo.AuthAccountVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.response.ResultCode;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.multishop.dto.ChangeAccountDTO;
import com.spud.nimbus.multishop.service.ShopUserAccountService;
import com.spud.nimbus.multishop.service.ShopUserService;
import com.spud.nimbus.multishop.vo.ShopUserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author FrozenWatermelon
 * @date 2020/09/02
 */
@RequestMapping(value = "/shop_user/account")
@RestController
@Tag(name = "店铺用户账号信息")
public class ShopUserAccountController {

	private final ShopUserAccountService shopUserAccountService;

	private final ShopUserService shopUserService;

	@Autowired
	public ShopUserAccountController(ShopUserAccountService shopUserAccountService, ShopUserService shopUserService) {
		this.shopUserAccountService = shopUserAccountService;
		this.shopUserService = shopUserService;
	}

	@GetMapping
	@Operation(summary = "获取账号信息", description = "获取账号信息")
	public Result<AuthAccountVO> getAccount(Long shopUserId) {
		return shopUserAccountService.getByUserIdAndSysType(shopUserId, AuthUserContext.get().getSysType());
	}

	@PostMapping
	@Operation(summary = "添加账号", description = "添加账号")
	public Result<Void> addAccount(@Valid @RequestBody ChangeAccountDTO changeAccountDTO) {
		ShopUserVO shopUserVO = shopUserService.getByUserId(changeAccountDTO.getUserId());
		if (shopUserVO == null) {
			return Result.showFailMsg("无法获取账户信息");
		}
		if (Objects.equals(shopUserVO.getHasAccount(), 1)) {
			return Result.showFailMsg("已有账号，无需重复添加");
		}
		if (!Objects.equals(shopUserVO.getShopId(), AuthUserContext.get().getTenantId())) {
			return Result.fail(ResultCode.UNAUTHORIZED, null);
		}
		return shopUserAccountService.save(changeAccountDTO);
	}

	@PutMapping
	@Operation(summary = "修改账号", description = "修改账号")
	public Result<Void> updateAccount(@Valid @RequestBody ChangeAccountDTO changeAccountDTO) {
		ShopUserVO shopUserVO = shopUserService.getByUserId(changeAccountDTO.getUserId());
		if (shopUserVO == null || Objects.equals(shopUserVO.getHasAccount(), 0)) {
			return Result.showFailMsg("无法获取账户信息");
		}
		if (!Objects.equals(shopUserVO.getShopId(), AuthUserContext.get().getTenantId())) {
			return Result.fail(ResultCode.UNAUTHORIZED, null);
		}
		return shopUserAccountService.update(changeAccountDTO);
	}

}
