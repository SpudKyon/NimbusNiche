package com.spud.nimbus.user.controller;

import com.spud.nimbus.api.auth.bo.UserInfoInTokenBO;
import com.spud.nimbus.api.user.vo.UserApiVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.response.ResultCode;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.user.model.User;
import com.spud.nimbus.user.service.UserService;
import com.spud.nimbus.user.vo.UserSimpleInfoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/1
 */
@RestController("appUserController")
@RequestMapping("/v1/user")
@Tag(name = "app-用户信息")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/simple_info")
	@Operation(summary = "用户头像昵称", description = "用户头像昵称")
	public Result<UserSimpleInfoVO> getByAddrId() {
		Long userId = AuthUserContext.get().getUserId();

		UserApiVO userApiVO = userService.getByUserId(userId);
		UserSimpleInfoVO userSimpleInfoVO = new UserSimpleInfoVO();
		userSimpleInfoVO.setNickName(userApiVO.getNickName());
		userSimpleInfoVO.setPic(userApiVO.getPic());

		return Result.success(userSimpleInfoVO);
	}

	@GetMapping("/ma/user_detail_info")
	@Operation(summary = "获取用户详细信息", description = "返回用户详细信息")
	public Result<UserApiVO> getUserDetailInfo() {
		UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
		if (userInfoInTokenBO == null) {
			return Result.fail(ResultCode.CLEAN_TOKEN, null);
		}
		Long userId = userInfoInTokenBO.getUserId();
		UserApiVO userApiVO = userService.getByUserId(userId);
		return Result.success(userApiVO);
	}

	@PostMapping("/ma/update_user")
	@Operation(summary = "更新用户信息")
	public Result<Void> updateUser(@RequestBody UserApiVO userApiVO) {
		Long userId = AuthUserContext.get().getUserId();
		UserApiVO byUserId = userService.getByUserId(userId);
		User user = new User();
		user.setUserId(userId);
		user.setNickName(Objects.isNull(userApiVO.getNickName()) ? byUserId.getNickName() : userApiVO.getNickName());
		user.setPic(Objects.isNull(userApiVO.getPic()) ? byUserId.getPic() : userApiVO.getPic());
		userService.update(user);
		return Result.success(null);
	}

}