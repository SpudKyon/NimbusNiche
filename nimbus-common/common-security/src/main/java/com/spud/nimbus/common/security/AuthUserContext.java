package com.spud.nimbus.common.security;

import com.spud.nimbus.api.auth.bo.UserInfoInTokenBO;

/**
 * @author spud
 * @date 2024/2/1
 */
public class AuthUserContext {

	/**
	 * The request holder.
	 */
	private static final ThreadLocal<UserInfoInTokenBO> USER_INFO_IN_TOKEN_HOLDER = new ThreadLocal<>();

	public static UserInfoInTokenBO get() {
		return USER_INFO_IN_TOKEN_HOLDER.get();
	}

	public static UserInfoInTokenBO forceGet() {
		return USER_INFO_IN_TOKEN_HOLDER.get();
	}

	public static void set(UserInfoInTokenBO userInfoInTokenBo) {
		USER_INFO_IN_TOKEN_HOLDER.set(userInfoInTokenBo);
	}

	public static void clean() {
		if (USER_INFO_IN_TOKEN_HOLDER.get() != null) {
			USER_INFO_IN_TOKEN_HOLDER.remove();
		}
	}

}
