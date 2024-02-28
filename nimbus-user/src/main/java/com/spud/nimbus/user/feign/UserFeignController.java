package com.spud.nimbus.user.feign;

import com.spud.nimbus.api.user.feign.UserFeignClient;
import com.spud.nimbus.api.user.vo.UserApiVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/1
 */
@RestController
public class UserFeignController implements UserFeignClient {

	@Autowired
	private UserService userService;

	@Override
	public Result<List<UserApiVO>> getUserByUserIds(List<Long> userIds) {
		List<UserApiVO> userList = userService.getUserByUserIds(userIds);
		return Result.success(userList);
	}

	@Override
	public Result<UserApiVO> getUserData(Long userId) {
		UserApiVO user = userService.getByUserId(userId);
		return Result.success(user);
	}

}