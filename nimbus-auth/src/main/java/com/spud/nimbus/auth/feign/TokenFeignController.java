package com.spud.nimbus.auth.feign;

import com.spud.nimbus.api.auth.bo.UserInfoInTokenBO;
import com.spud.nimbus.api.auth.feign.TokenFeignClient;
import com.spud.nimbus.auth.manage.TokenStore;
import com.spud.nimbus.common.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author spud
 * @date 2024/1/25
 */
@RestController
@Slf4j
public class TokenFeignController implements TokenFeignClient {

  @Autowired
  private TokenStore tokenStore;

  @Override
  public Result<UserInfoInTokenBO> checkToken(String accessToken) {
    Result<UserInfoInTokenBO> userInfoByAccessTokenResponse = tokenStore
            .getUserInfoByAccessToken(accessToken, true);
    if (!userInfoByAccessTokenResponse.isSuccess()) {
      return Result.transform(userInfoByAccessTokenResponse);
    }
    return Result.success(userInfoByAccessTokenResponse.getData());
  }

}
