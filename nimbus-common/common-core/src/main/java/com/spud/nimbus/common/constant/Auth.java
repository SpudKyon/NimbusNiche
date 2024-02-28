package com.spud.nimbus.common.constant;

import com.spud.nimbus.common.feign.FeignInsideAuthConfig;

/**
 * @author spud
 * @date 2024/1/28
 */
public interface Auth {

	String CHECK_TOKEN_URI = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/token/checkToken";

	String CHECK_RBAC_URI = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/permission/checkPermission";

}
