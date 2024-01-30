package com.spud.nimbus.api.auth.feign;

import com.spud.nimbus.api.auth.bo.UserInfoInTokenBO;
import com.spud.nimbus.common.constant.Auth;
import com.spud.nimbus.common.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author spud
 * @date 2024/1/25
 */
@FeignClient(value = "nimbus-auth",contextId ="token")
public interface TokenFeignClient {

	/**
	 * 校验token并返回token保存的用户信息
	 * @param accessToken accessToken
	 * @return token保存的用户信息
	 */
	@GetMapping(value = Auth.CHECK_TOKEN_URI)
	Result<UserInfoInTokenBO> checkToken(@RequestParam("accessToken") String accessToken);

}
