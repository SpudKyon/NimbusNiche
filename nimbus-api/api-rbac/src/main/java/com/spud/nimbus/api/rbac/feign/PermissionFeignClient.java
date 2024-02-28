package com.spud.nimbus.api.rbac.feign;

import com.spud.nimbus.api.rbac.dto.ClearUserPermissionsCacheDTO;
import com.spud.nimbus.common.constant.Auth;
import com.spud.nimbus.common.feign.FeignInsideAuthConfig;
import com.spud.nimbus.common.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author spud
 * @date 2024/2/9
 */
@FeignClient(value = PermissionFeignClient.SERVICE_NAME, contextId = "permission")
public interface PermissionFeignClient {

	String SERVICE_NAME = "nimbus-rbac";

	/**
	 * 校验是否有某个uri的权限
	 * @return 是否有某个uri的权限
	 */
	@GetMapping(value = Auth.CHECK_RBAC_URI)
	Result<Boolean> checkPermission(@RequestParam("userId") Long userId, @RequestParam("sysType") Integer sysType,
			@RequestParam("uri") String uri, @RequestParam("isAdmin") Integer isAdmin,
			@RequestParam("method") Integer method);

	/**
	 * 清除用户权限缓存
	 */
	@PostMapping(
			value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/permission/clearUserPermissionsCache")
	Result<Void> clearUserPermissionsCache(@RequestBody ClearUserPermissionsCacheDTO clearUserPermissionsCacheDTO);

}
