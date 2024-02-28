package com.spud.nimbus.common.security.bo;

import com.spud.nimbus.api.auth.bo.UserInfoInTokenBO;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/9
 */
@Data
@ToString
public class AuthAccountInVerifyBO extends UserInfoInTokenBO {

	private String password;

	private Integer status;

}
