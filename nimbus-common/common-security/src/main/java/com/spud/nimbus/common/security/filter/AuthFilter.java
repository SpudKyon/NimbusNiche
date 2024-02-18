package com.spud.nimbus.common.security.filter;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.spud.nimbus.api.auth.bo.UserInfoInTokenBO;
import com.spud.nimbus.api.auth.constant.SysTypeEnum;
import com.spud.nimbus.api.auth.feign.TokenFeignClient;
import com.spud.nimbus.api.rbac.constant.HttpMethodEnum;
import com.spud.nimbus.api.rbac.feign.PermissionFeignClient;
import com.spud.nimbus.common.constant.Auth;
import com.spud.nimbus.common.feign.FeignInsideAuthConfig;
import com.spud.nimbus.common.handler.HttpHandler;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.response.ResultCode;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.common.security.adapter.AuthConfigAdapter;
import com.spud.nimbus.common.util.IpHelper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/9
 */
@Slf4j
@Component
public class AuthFilter implements Filter {

  @Autowired
  private AuthConfigAdapter authConfigAdapter;

  @Autowired
  private HttpHandler httpHandler;

  @Autowired
  private TokenFeignClient tokenFeignClient;

  @Autowired
  private PermissionFeignClient permissionFeignClient;

  @Autowired
  private FeignInsideAuthConfig feignInsideAuthConfig;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;

    if (!feignRequestCheck(req)) {
      httpHandler.printServerResponseToWeb(Result.fail(ResultCode.UNAUTHORIZED, null));
      return;
    }

    if (Auth.CHECK_TOKEN_URI.equals(req.getRequestURI())) {
      chain.doFilter(req, resp);
      return;
    }


    List<String> excludePathPatterns = authConfigAdapter.excludePathPatterns();

    // 如果匹配不需要授权的路径，就不需要校验是否需要授权
    if (CollectionUtil.isNotEmpty(excludePathPatterns)) {
      for (String excludePathPattern : excludePathPatterns) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        if (pathMatcher.match(excludePathPattern, req.getRequestURI())) {
          chain.doFilter(req, resp);
          return;
        }
      }
    }

    String accessToken = req.getHeader("Authorization");

    if (StrUtil.isBlank(accessToken)) {
      httpHandler.printServerResponseToWeb(Result.fail(ResultCode.UNAUTHORIZED, null));
      return;
    }

    // 校验token，并返回用户信息
    Result<UserInfoInTokenBO> userInfoInTokenVoResult = tokenFeignClient
            .checkToken(accessToken);
    if (!userInfoInTokenVoResult.isSuccess()) {
      httpHandler.printServerResponseToWeb(Result.fail(ResultCode.UNAUTHORIZED, null));
      return;
    }

    UserInfoInTokenBO userInfoInToken = userInfoInTokenVoResult.getData();

    // 需要用户角色权限，就去根据用户角色权限判断是否
    if (!checkRbac(userInfoInToken, req.getRequestURI(), req.getMethod())) {
      httpHandler.printServerResponseToWeb(Result.fail(ResultCode.UNAUTHORIZED, null));
      return;
    }

    try {
      // 保存上下文
      AuthUserContext.set(userInfoInToken);

      chain.doFilter(req, resp);
    } finally {
      AuthUserContext.clean();
    }

  }

  private boolean feignRequestCheck(HttpServletRequest req) {
    // 不是feign请求，不用校验
    if (!req.getRequestURI().startsWith(FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX)) {
      return true;
    }
    String feignInsideSecret = req.getHeader(feignInsideAuthConfig.getKey());

    // 校验feign 请求携带的key 和 value是否正确
    if (StrUtil.isBlank(feignInsideSecret) || !Objects.equals(feignInsideSecret, feignInsideAuthConfig.getSecret())) {
      return false;
    }
    // ip白名单
    List<String> ips = feignInsideAuthConfig.getIps();
    // 移除无用的空ip
    ips.removeIf(StrUtil::isBlank);
    // 有ip白名单，且ip不在白名单内，校验失败
    if (CollectionUtil.isNotEmpty(ips)
            && !ips.contains(IpHelper.getIpAddr())) {
      log.error("ip not in ip White list: {}, ip, {}", ips, IpHelper.getIpAddr());
      return false;
    }
    return true;
  }

  /**
   * 用户角色权限校验
   *
   * @param uri uri
   * @return 是否校验成功
   */
  public boolean checkRbac(UserInfoInTokenBO userInfoInToken, String uri, String method) {

    if (!Objects.equals(SysTypeEnum.PLATFORM.value(), userInfoInToken.getSysType()) && !Objects.equals(SysTypeEnum.MULTISHOP.value(), userInfoInToken.getSysType())) {
      return true;
    }

    Result<Boolean> booleanResult = permissionFeignClient
            .checkPermission(userInfoInToken.getUserId(), userInfoInToken.getSysType(), uri, userInfoInToken.getIsAdmin(), HttpMethodEnum.valueOf(method.toUpperCase()).value());

    if (!booleanResult.isSuccess()) {
      return false;
    }

    return booleanResult.getData();
  }
}
