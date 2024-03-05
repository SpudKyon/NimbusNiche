package com.spud.nimbus.user.controller.app;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spud.nimbus.common.exception.NimbusException;
import com.spud.nimbus.common.order.vo.UserAddrVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.response.ResultCode;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.common.util.BeanUtil;
import com.spud.nimbus.user.dto.UserAddrDTO;
import com.spud.nimbus.user.model.UserAddr;
import com.spud.nimbus.user.service.UserAddrService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/1
 */
@RestController("UserAddrController")
@RequestMapping("/user_addr")
@Tag(name = "app-用户地址")
public class UserAddrController {

	@Autowired
	private UserAddrService userAddrService;

	private static final Integer MAX_USER_ADDR = 10;

	@GetMapping("/list")
	@Operation(summary = "获取用户地址列表", description = "获取用户地址列表")
	public Result<List<UserAddrVO>> list() {
		Long userId = AuthUserContext.get().getUserId();
		List<UserAddrVO> userAddrPage = userAddrService.userAddrList(userId);
		return Result.success(userAddrPage);
	}

	@GetMapping
	@Operation(summary = "获取用户地址", description = "根据addrId获取用户地址")
	public Result<UserAddrVO> getByAddrId(@RequestParam Long addrId) {
		return Result.success(userAddrService.getUserAddrByUserId(addrId, AuthUserContext.get().getUserId()));
	}

	@PostMapping
	@Operation(summary = "保存用户地址", description = "保存用户地址")
	public Result<Void> save(@Valid @RequestBody UserAddrDTO userAddrDTO) {
		Long userId = AuthUserContext.get().getUserId();
		int userAddrCount = userAddrService.countByUserId(userId);
		if (userAddrCount >= MAX_USER_ADDR) {
			return Result.showFailMsg("收货地址已达到上限，无法再新增地址");
		}
		UserAddr userAddr = BeanUtil.map(userAddrDTO, UserAddr.class);
		if (userAddrCount == 0) {
			userAddr.setIsDefault(UserAddr.DEFAULT_ADDR);
		}
		else if (!UserAddr.DEFAULT_ADDR.equals(userAddr.getIsDefault())) {
			userAddr.setIsDefault(UserAddr.NOT_DEFAULT_ADDR);
		}
		userAddr.setAddrId(null);
		userAddr.setUserId(userId);
		userAddrService.save(userAddr);

		return Result.success(null);
	}

	@PutMapping
	@Operation(summary = "更新用户地址", description = "更新用户地址")
	public Result<Void> update(@Valid @RequestBody UserAddrDTO userAddrDTO) {
		Long userId = AuthUserContext.get().getUserId();
		UserAddrVO dbUserAddr = userAddrService.getUserAddrByUserId(userAddrDTO.getAddrId(), userId);
		if (dbUserAddr == null) {
			throw new NimbusException("该地址已被删除");
		}
		// 默认地址不能修改为普通地址
		else if (dbUserAddr.getIsDefault().equals(UserAddr.DEFAULT_ADDR)
				&& userAddrDTO.getIsDefault().equals(UserAddr.NOT_DEFAULT_ADDR)) {
			throw new NimbusException(ResultCode.DATA_ERROR);
		}
		UserAddr userAddr = BeanUtil.map(userAddrDTO, UserAddr.class);
		userAddr.setUserId(userId);
		userAddrService.update(userAddr);
		return Result.success(null);
	}

	@DeleteMapping
	@Operation(summary = "删除用户地址", description = "根据用户地址id删除用户地址")
	public Result<Void> delete(@RequestParam Long addrId) {
		Long userId = AuthUserContext.get().getUserId();
		UserAddrVO dbUserAddr = userAddrService.getUserAddrByUserId(addrId, userId);
		if (dbUserAddr == null) {
			throw new NimbusException("该地址已被删除");
		}
		else if (dbUserAddr.getIsDefault().equals(UserAddr.DEFAULT_ADDR)) {
			throw new NimbusException("默认地址不能删除");
		}
		userAddrService.deleteUserAddrByUserId(addrId, userId);
		return Result.success(null);
	}

}
