package com.spud.nimbus.auth.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.api.auth.bo.UserInfoInTokenBO;
import com.spud.nimbus.auth.constant.AuthAccountStatusEnum;
import com.spud.nimbus.auth.mapper.AuthAccountMapper;
import com.spud.nimbus.auth.model.AuthAccount;
import com.spud.nimbus.auth.service.AuthAccountService;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.security.bo.AuthAccountInVerifyBO;
import com.spud.nimbus.common.security.constant.InputUserNameEnum;
import com.spud.nimbus.common.util.BeanUtil;
import com.spud.nimbus.common.util.PrincipalUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 统一账户信息 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class AuthAccountServiceImpl extends ServiceImpl<AuthAccountMapper, AuthAccount> implements AuthAccountService {
  public static final String USER_NOT_FOUND_SECRET = "USER_NOT_FOUND_SECRET";
  private static String userNotFoundEncodedPassword;
  @Resource
  private AuthAccountMapper authAccountMapper;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public Result<UserInfoInTokenBO> getUserInfoInTokenByInputUserNameAndPassword(String inputUserName,
                                                                                String password, Integer sysType) {

    if (StrUtil.isBlank(inputUserName)) {
      return Result.showFailMsg("用户名不能为空");
    }
    if (StrUtil.isBlank(password)) {
      return Result.showFailMsg("密码不能为空");
    }

    InputUserNameEnum inputUserNameEnum = null;

    // 用户名
    if (PrincipalUtil.isUserName(inputUserName)) {
      inputUserNameEnum = InputUserNameEnum.USERNAME;
    }

    if (inputUserNameEnum == null) {
      return Result.showFailMsg("请输入正确的用户名");
    }

    AuthAccountInVerifyBO authAccountInVerifyBO = authAccountMapper
            .getAuthAccountInVerifyByInputUserName(inputUserNameEnum.value(), inputUserName, sysType);

    if (authAccountInVerifyBO == null) {
      prepareTimingAttackProtection();
      // 再次进行运算，防止计时攻击
      // 计时攻击（Timing
      // attack），通过设备运算的用时来推断出所使用的运算操作，或者通过对比运算的时间推定数据位于哪个存储设备，或者利用通信的时间差进行数据窃取。
      mitigateAgainstTimingAttack(password);
      return Result.showFailMsg("用户名或密码不正确");
    }

    if (Objects.equals(authAccountInVerifyBO.getStatus(), AuthAccountStatusEnum.DISABLE.value())) {
      return Result.showFailMsg("用户已禁用，请联系客服");
    }

    if (!passwordEncoder.matches(password, authAccountInVerifyBO.getPassword())) {
      return Result.showFailMsg("用户名或密码不正确");
    }

    return Result.success(BeanUtil.map(authAccountInVerifyBO, UserInfoInTokenBO.class));
  }

  @Override
  public AuthAccount getByUserIdAndType(Long userId, Integer sysType) {
    return authAccountMapper.getByUserIdAndType(userId, sysType);
  }

  @Override
  public void updatePassword(Long userId, Integer sysType, String newPassWord) {
    authAccountMapper.updatePassword(userId, sysType, passwordEncoder.encode(newPassWord));
  }

  @Override
  public AuthAccount getByUid(Long uid) {
    return authAccountMapper.getByUid(uid);
  }

  @Override
  public AuthAccount getAccountByInputUserName(String mobile, Integer systemType) {
    return authAccountMapper.getAccountByInputUserName(mobile, systemType);
  }

  /**
   * 防止计时攻击
   */
  private void prepareTimingAttackProtection() {
    if (userNotFoundEncodedPassword == null) {
      userNotFoundEncodedPassword = this.passwordEncoder.encode(USER_NOT_FOUND_SECRET);
    }
  }

  /**
   * 防止计时攻击
   */
  private void mitigateAgainstTimingAttack(String presentedPassword) {
    if (presentedPassword != null) {
      this.passwordEncoder.matches(presentedPassword, userNotFoundEncodedPassword);
    }
  }
}
