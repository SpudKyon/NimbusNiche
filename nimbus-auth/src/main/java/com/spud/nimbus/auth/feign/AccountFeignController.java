package com.spud.nimbus.auth.feign;

import cn.hutool.core.util.StrUtil;
import com.spud.nimbus.api.auth.bo.UserInfoInTokenBO;
import com.spud.nimbus.api.auth.constant.SysTypeEnum;
import com.spud.nimbus.api.auth.dto.AuthAccountDTO;
import com.spud.nimbus.api.auth.feign.AccountFeignClient;
import com.spud.nimbus.api.auth.vo.AuthAccountVO;
import com.spud.nimbus.api.auth.vo.TokenInfoVO;
import com.spud.nimbus.api.leaf.feign.SegmentFeignClient;
import com.spud.nimbus.auth.manage.TokenStore;
import com.spud.nimbus.auth.mapper.AuthAccountMapper;
import com.spud.nimbus.auth.model.AuthAccount;
import com.spud.nimbus.common.exception.NimbusException;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.response.ResultCode;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.common.security.bo.AuthAccountInVerifyBO;
import com.spud.nimbus.common.security.constant.InputUserNameEnum;
import com.spud.nimbus.common.util.BeanUtil;
import com.spud.nimbus.common.util.PrincipalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author FrozenWatermelon
 * @date 2020/9/22
 */
@RestController
public class AccountFeignController implements AccountFeignClient {

  @Autowired
  private AuthAccountMapper authAccountMapper;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private TokenStore tokenStore;

  @Autowired
  private SegmentFeignClient segmentFeignClient;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Long> save(AuthAccountDTO authAccountDTO) {
    Result<Long> segmentIdResponse = segmentFeignClient.getSegmentId("mall4cloud-auth-account");
    if (!segmentIdResponse.isSuccess()) {
      throw new NimbusException(ResultCode.EXCEPTION);
    }

    Result<AuthAccount> verify = verify(authAccountDTO);
    if (!verify.isSuccess()) {
      return Result.transform(verify);
    }
    AuthAccount data = verify.getData();
    data.setUid(segmentIdResponse.getData());
    authAccountMapper.save(data);

    return Result.success(data.getUid());
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Void> update(AuthAccountDTO authAccountDTO) {
    Result<AuthAccount> verify = verify(authAccountDTO);
    if (!verify.isSuccess()) {
      return Result.transform(verify);
    }
    authAccountMapper.updateAccountInfo(verify.getData());
    return Result.success(null);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Void> updateAuthAccountStatus(AuthAccountDTO authAccountDTO) {
    if (Objects.isNull(authAccountDTO.getStatus())) {
      throw new NimbusException(ResultCode.EXCEPTION);
    }
    AuthAccount authAccount = BeanUtil.map(authAccountDTO, AuthAccount.class);
    authAccountMapper.updateAccountInfo(authAccount);
    return Result.success(null);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Void> deleteByUserIdAndSysType(Long userId) {
    UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
    authAccountMapper.deleteByUserIdAndSysType(userId, userInfoInTokenBO.getSysType());
    return Result.success(null);
  }

  @Override
  public Result<AuthAccountVO> getByUserIdAndSysType(Long userId, Integer sysType) {
    UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
    AuthAccount authAccount = authAccountMapper.getByUserIdAndType(userId, userInfoInTokenBO.getSysType());
    return Result.success(BeanUtil.map(authAccount, AuthAccountVO.class));
  }

  @Override
  public Result<TokenInfoVO> storeTokenAndGetVo(UserInfoInTokenBO userInfoInTokenBO) {
    return Result.success(tokenStore.storeAndGetVo(userInfoInTokenBO));
  }

  @Override
  public Result<AuthAccountVO> getByUsernameAndSysType(String username, SysTypeEnum sysType) {
    return Result.success(authAccountMapper.getByUsernameAndSysType(username, sysType.value()));
  }

  private Result<AuthAccount> verify(AuthAccountDTO authAccountDTO) {

    // 用户名
    if (!PrincipalUtil.isUserName(authAccountDTO.getUsername())) {
      return Result.showFailMsg("用户名格式不正确");
    }

    AuthAccountInVerifyBO userNameBo = authAccountMapper.getAuthAccountInVerifyByInputUserName(InputUserNameEnum.USERNAME.value(), authAccountDTO.getUsername(), authAccountDTO.getSysType());
    if (userNameBo != null && !Objects.equals(userNameBo.getUserId(), authAccountDTO.getUserId())) {
      return Result.showFailMsg("用户名已存在，请更换用户名再次尝试");
    }

    AuthAccount authAccount = BeanUtil.map(authAccountDTO, AuthAccount.class);

    if (StrUtil.isNotBlank(authAccount.getPassword())) {
      authAccount.setPassword(passwordEncoder.encode(authAccount.getPassword()));
    }

    return Result.success(authAccount);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Void> updateUserInfoByUserIdAndSysType(UserInfoInTokenBO userInfoInTokenBO, Long userId, Integer sysType) {
    AuthAccount byUserIdAndType = authAccountMapper.getByUserIdAndType(userId, sysType);
    userInfoInTokenBO.setUid(byUserIdAndType.getUid());
    tokenStore.updateUserInfoByUidAndAppId(byUserIdAndType.getUid(), sysType.toString(), userInfoInTokenBO);
    AuthAccount authAccount = BeanUtil.map(userInfoInTokenBO, AuthAccount.class);
    int res = authAccountMapper.updateUserInfoByUserId(authAccount, userId, sysType);
    if (res != 1) {
      throw new NimbusException("用户信息错误，更新失败");
    }
    return Result.success(null);
  }

  @Override
  public Result<AuthAccountVO> getMerchantInfoByTenantId(Long tenantId) {
    AuthAccountVO authAccountVO = authAccountMapper.getMerchantInfoByTenantId(tenantId);
    return Result.success(authAccountVO);
  }

}
