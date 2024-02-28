package com.spud.nimbus.auth.controller;

import com.spud.nimbus.api.auth.bo.UserInfoInTokenBO;
import com.spud.nimbus.api.auth.vo.TokenInfoVO;
import com.spud.nimbus.api.rbac.dto.ClearUserPermissionsCacheDTO;
import com.spud.nimbus.api.rbac.feign.PermissionFeignClient;
import com.spud.nimbus.auth.dto.AuthenticationDTO;
import com.spud.nimbus.auth.manage.TokenStore;
import com.spud.nimbus.auth.service.AuthAccountService;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.response.ResultCode;
import com.spud.nimbus.common.security.AuthUserContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author spud
 * @date 2024/2/17
 */
@RestController
@Tag(name = "登录")
public class LoginController {

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private AuthAccountService authAccountService;

	@Autowired
	private PermissionFeignClient permissionFeignClient;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("v1/ua/login")
	@Operation(summary = "账号密码", description = "通过账号登录，还要携带用户的类型，也就是用户所在的系统")
	public Result<TokenInfoVO> login(@Valid @RequestBody AuthenticationDTO authenticationDTO) {

		// 这边获取了用户的用户信息，那么根据sessionid对应一个user的原则，我应该要把这个东西存起来，然后校验，那么存到哪里呢？
		// redis，redis有天然的自动过期的机制，有key value的形式
		Result<UserInfoInTokenBO> userInfoInTokenResponse = authAccountService
				.getUserInfoInTokenByInputUserNameAndPassword(authenticationDTO.getPrincipal(),
						authenticationDTO.getCredentials(), authenticationDTO.getSysType());

		if (!userInfoInTokenResponse.isSuccess()) {
			return Result.transform(userInfoInTokenResponse);
		}

		UserInfoInTokenBO data = userInfoInTokenResponse.getData();

		ClearUserPermissionsCacheDTO clearUserPermissionsCacheDTO = new ClearUserPermissionsCacheDTO();
		clearUserPermissionsCacheDTO.setSysType(data.getSysType());
		clearUserPermissionsCacheDTO.setUserId(data.getUserId());
		// 将以前的权限清理了,以免权限有缓存
		Result<Void> clearResponseEntity = permissionFeignClient
				.clearUserPermissionsCache(clearUserPermissionsCacheDTO);

		if (!clearResponseEntity.isSuccess()) {
			return Result.fail(ResultCode.UNAUTHORIZED, null);
		}

		// 保存token，返回token数据给前端，这里是最重要的
		return Result.success(tokenStore.storeAndGetVo(data));
	}

	@PostMapping("v1/login_out")
	@Operation(summary = "退出登陆", description = "点击退出登陆，清除token，清除菜单缓存")
	public Result<TokenInfoVO> loginOut() {
		UserInfoInTokenBO userInfoInToken = AuthUserContext.get();
		ClearUserPermissionsCacheDTO clearUserPermissionsCacheDTO = new ClearUserPermissionsCacheDTO();
		clearUserPermissionsCacheDTO.setSysType(userInfoInToken.getSysType());
		clearUserPermissionsCacheDTO.setUserId(userInfoInToken.getUserId());
		// 将以前的权限清理了,以免权限有缓存
		permissionFeignClient.clearUserPermissionsCache(clearUserPermissionsCacheDTO);
		// 删除该用户在该系统的token
		tokenStore.deleteAllToken(userInfoInToken.getSysType().toString(), userInfoInToken.getUid());
		return Result.success(null);
	}

}