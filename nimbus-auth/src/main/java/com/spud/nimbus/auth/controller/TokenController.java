package com.spud.nimbus.auth.controller;

import com.spud.nimbus.api.auth.vo.TokenInfoVO;
import com.spud.nimbus.auth.dto.RefreshTokenDTO;
import com.spud.nimbus.auth.manage.TokenStore;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.security.bo.TokenInfoBO;
import com.spud.nimbus.common.util.BeanUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author spud
 * @date 2024/2/17
 */
@RestController
@Tag(name = "token")
public class TokenController {

	@Autowired
	private TokenStore tokenStore;

	@PostMapping("/ua/token/refresh")
	public Result<TokenInfoVO> refreshToken(@Valid @RequestBody RefreshTokenDTO refreshTokenDTO) {
		Result<TokenInfoBO> tokenInfoResult = tokenStore.refreshToken(refreshTokenDTO.getRefreshToken());
		if (!tokenInfoResult.isSuccess()) {
			return Result.transform(tokenInfoResult);
		}
		return Result.success(BeanUtil.map(tokenInfoResult.getData(), TokenInfoVO.class));
	}

}
