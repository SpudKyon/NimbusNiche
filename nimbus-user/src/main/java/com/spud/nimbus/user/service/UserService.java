package com.spud.nimbus.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spud.nimbus.api.user.vo.UserApiVO;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.user.dto.UserRegisterDTO;
import com.spud.nimbus.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author spud
 * @since 2024-01-24
 */
@Service
public interface UserService extends IService<User> {
  /**
   * 分页获取用户表列表
   *
   * @param pageDTO 分页参数
   * @return 用户表列表分页数据
   */
  PageVO<UserApiVO> page(PageDTO pageDTO);

  /**
   * 根据用户表id获取用户表
   *
   * @param userId 用户表id
   * @return 用户表
   */
  UserApiVO getByUserId(Long userId);

  /**
   * 更新用户表
   *
   * @param user 用户表
   */
  void update(User user);

  /**
   * 根据用户id列表，获取用户信息
   *
   * @param userIds 用户id列表
   * @return 用户信息
   */
  List<UserApiVO> getUserByUserIds(List<Long> userIds);

  /**
   * 保存用户
   *
   * @param param 注册信息
   * @return uid
   */
  Long save(UserRegisterDTO param);
}
