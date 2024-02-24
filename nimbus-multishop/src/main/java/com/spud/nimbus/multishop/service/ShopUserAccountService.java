package com.spud.nimbus.multishop.service;

import com.spud.nimbus.api.auth.vo.AuthAccountVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.multishop.dto.ChangeAccountDTO;

/**
 * @author spud
 * @date 2024/2/24
 */
public interface ShopUserAccountService {

  /**
   * 添加账户
   *
   * @param changeAccountDTO 账户信息
   * @return void
   */
  Result<Void> save(ChangeAccountDTO changeAccountDTO);

  /**
   * 更新账户
   *
   * @param changeAccountDTO 账户信息
   * @return
   */
  Result<Void> update(ChangeAccountDTO changeAccountDTO);

  /**
   * 根据用户id和系统类型获取用户信息
   *
   * @param userId  用户id
   * @param sysType 系统类型
   * @return void
   */
  Result<AuthAccountVO> getByUserIdAndSysType(Long userId, Integer sysType);
}
