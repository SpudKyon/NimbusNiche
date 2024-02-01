package com.spud.nimbus.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.common.order.vo.UserAddrVO;
import com.spud.nimbus.user.mapper.UserAddrMapper;
import com.spud.nimbus.user.model.UserAddr;
import com.spud.nimbus.user.service.UserAddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户地址 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-24
 */
@Service
public class UserAddrServiceImpl extends ServiceImpl<UserAddrMapper, UserAddr> implements UserAddrService {

  @Autowired
  private UserAddrMapper userAddrMapper;

  @Override
  public List<UserAddrVO> userAddrList(Long userId) {
    return userAddrMapper.list(userId);
  }

  @Override
  public boolean save(UserAddr userAddr) {
    if (userAddr.getIsDefault().equals(UserAddr.DEFAULT_ADDR)) {
      userAddrMapper.removeDefaultUserAddr(userAddr.getUserId());
    }
    return userAddrMapper.save(userAddr);
  }

  @Override
  public void update(UserAddr userAddr) {
    if (userAddr.getIsDefault().equals(UserAddr.DEFAULT_ADDR)) {
      userAddrMapper.removeDefaultUserAddr(userAddr.getUserId());
    }
    userAddrMapper.update(userAddr);
  }

  @Override
  public void deleteUserAddrByUserId(Long addrId, Long userId) {
    userAddrMapper.deleteById(addrId,userId);
  }

  @Override
  public UserAddrVO getUserAddrByUserId(Long addrId, Long userId) {
    // 获取用户默认地址
    if (addrId == 0) {
      return userAddrMapper.getUserDefaultAddrByUserId(userId);
    }
    return userAddrMapper.getByAddrId(addrId,userId);
  }

  @Override
  public int countByUserId(Long userId) {
    return userAddrMapper.countByUserId(userId);
  }
}
