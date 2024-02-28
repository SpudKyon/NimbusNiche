package com.spud.nimbus.api.rbac.feign;

import com.spud.nimbus.api.rbac.dto.UserRoleDTO;
import com.spud.nimbus.common.feign.FeignInsideAuthConfig;
import com.spud.nimbus.common.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/9
 */
@FeignClient(value = "nimbus-rbac", contextId = "userRole")
public interface UserRoleFeignClient {

	/**
	 * 保存用户角色关联信息
	 * @param userRoleDTO 用户角色关联信息
	 * @return void
	 */
	@PostMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/userRole/saveByUserIdAndSysType")
	Result<Void> saveByUserIdAndSysType(@RequestBody UserRoleDTO userRoleDTO);

	/**
	 * 更新用户角色关联信息
	 * @param userRoleDTO 用户角色关联信息
	 * @return void
	 */
	@PutMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/userRole/updateByUserIdAndSysType")
	Result<Void> updateByUserIdAndSysType(@RequestBody UserRoleDTO userRoleDTO);

	/**
	 * 删除用户角色关联信息
	 * @param userId 用户id
	 * @return void
	 */
	@DeleteMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/userRole/deleteByUserIdAndSysType")
	Result<Void> deleteByUserIdAndSysType(@RequestParam("userId") Long userId);

	/**
	 * 获取用户角色关联信息
	 * @param userId 用户id
	 * @return 用户角色关联ids
	 */
	@GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/userRole/getRoleIds")
	Result<List<Long>> getRoleIds(@RequestParam("userId") Long userId);

}