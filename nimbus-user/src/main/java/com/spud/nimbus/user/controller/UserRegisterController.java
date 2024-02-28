package com.spud.nimbus.user.controller;

import cn.hutool.core.util.StrUtil;
import com.spud.nimbus.api.auth.bo.UserInfoInTokenBO;
import com.spud.nimbus.api.auth.constant.SysTypeEnum;
import com.spud.nimbus.api.auth.feign.AccountFeignClient;
import com.spud.nimbus.api.auth.vo.TokenInfoVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.user.dto.UserRegisterDTO;
import com.spud.nimbus.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author spud
 * @date 2024/2/1
 */
@RestController
@RequestMapping("/v1/user/register")
@Tag(name = "app-用户注册接口")
public class UserRegisterController {

	@Autowired
	private UserService userService;

	@Autowired
	private AccountFeignClient accountFeignClient;

	@Operation(summary = "注册")
	@PostMapping
	public Result<TokenInfoVO> register(@Valid @RequestBody UserRegisterDTO param) {

		if (StrUtil.isBlank(param.getNickName())) {
			param.setNickName(param.getUserName());
		}
		// 1. 保存账户信息
		Long uid = userService.save(param);
		// 2. 登录
		UserInfoInTokenBO userInfoInTokenBO = new UserInfoInTokenBO();
		userInfoInTokenBO.setUid(uid);
		userInfoInTokenBO.setUserId(param.getUserId());
		userInfoInTokenBO.setSysType(SysTypeEnum.ORDINARY.value());
		userInfoInTokenBO.setIsAdmin(0);
		return accountFeignClient.storeTokenAndGetVo(userInfoInTokenBO);
	}

}
