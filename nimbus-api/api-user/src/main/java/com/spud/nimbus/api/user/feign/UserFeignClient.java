package com.spud.nimbus.api.user.feign;

import com.spud.nimbus.api.user.vo.UserApiVO;
import com.spud.nimbus.common.feign.FeignInsideAuthConfig;
import com.spud.nimbus.common.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author spud
 * @date 2024/1/31
 */
@FeignClient(value = "nimbus-user", contextId = "user")
public interface UserFeignClient {

	/**
	 * 根据用户id列表，获取用户信息
	 * @param userIds 用户id列表
	 * @return 用户列表信息
	 */
	@GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/user/getUserByUserIds")
	Result<List<UserApiVO>> getUserByUserIds(@RequestParam("userId") List<Long> userIds);

	/**
	 * 获取用户数据
	 * @param userId 用户id
	 * @return 用户数据
	 */
	@GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/user/getUserData")
	Result<UserApiVO> getUserData(Long userId);

}