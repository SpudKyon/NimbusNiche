package com.spud.nimbus.rbac.feign;

import com.spud.nimbus.api.rbac.bo.UriPermissionBO;
import com.spud.nimbus.api.rbac.dto.ClearUserPermissionsCacheDTO;
import com.spud.nimbus.api.rbac.feign.PermissionFeignClient;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.response.ResultCode;
import com.spud.nimbus.common.util.BooleanUtil;
import com.spud.nimbus.rbac.service.MenuPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/22
 */
@RestController
@Slf4j
public class PermissionFeignController implements PermissionFeignClient {

	private final MenuPermissionService menuPermissionService;

	@Autowired
	public PermissionFeignController(MenuPermissionService menuPermissionService) {
		this.menuPermissionService = menuPermissionService;
	}

	@Override
	public Result<Boolean> checkPermission(@RequestParam("userId") Long userId,
			@RequestParam("sysType") Integer sysType, @RequestParam("uri") String uri,
			@RequestParam("isAdmin") Integer isAdmin, @RequestParam("method") Integer method) {
		// 目前该用户拥有的权限
		List<String> userPermissions = menuPermissionService.listUserPermissions(userId, sysType,
				BooleanUtil.isTrue(isAdmin));

		// uri,方法对应的权限 map
		List<UriPermissionBO> uriPermissions = menuPermissionService.listUriPermissionInfo(sysType);

		AntPathMatcher pathMatcher = new AntPathMatcher();

		// 看看该uri对应需要什么权限
		for (UriPermissionBO uriPermission : uriPermissions) {
			// uri + 请求方式匹配
			if (pathMatcher.match(uriPermission.getUri(), uri) && Objects.equals(uriPermission.getMethod(), method)) {
				// uri 用户有这个权限
				if (userPermissions.contains(uriPermission.getPermission())) {
					return Result.success(Boolean.TRUE);
				}
				else {
					return Result.fail(ResultCode.UNAUTHORIZED, null);
				}
			}
		}

		// 如果uri 没有匹配到，则说明uri不需要权限，直接校验成功
		return Result.success(Boolean.TRUE);
	}

	@Override
	public Result<Void> clearUserPermissionsCache(ClearUserPermissionsCacheDTO clearUserPermissionsCacheDTO) {
		menuPermissionService.clearUserPermissionsCache(clearUserPermissionsCacheDTO.getUserId(),
				clearUserPermissionsCacheDTO.getSysType());
		return Result.success(null);
	}

}
