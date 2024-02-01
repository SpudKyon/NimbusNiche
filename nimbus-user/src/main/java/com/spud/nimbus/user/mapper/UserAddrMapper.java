package com.spud.nimbus.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spud.nimbus.common.order.vo.UserAddrVO;
import com.spud.nimbus.user.model.UserAddr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户地址 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-24
 */
@Mapper
public interface UserAddrMapper extends BaseMapper<UserAddr> {
  /**
   * 获取用户地址列表
   *
   * @param userId
   * @return 用户地址列表
   */
  List<UserAddrVO> list(@Param("userId") Long userId);

  /**
   * 根据用户地址id获取用户地址
   *
   * @param userId 用户id
   * @param addrId 用户地址id
   * @return 用户地址
   */
  UserAddrVO getByAddrId(@Param("addrId") Long addrId, @Param("userId") Long userId);

  /**
   * 保存用户地址
   *
   * @param userAddr 用户地址
   */
  boolean save(@Param("userAddr") UserAddr userAddr);

  /**
   * 更新用户地址
   *
   * @param userAddr 用户地址
   */
  void update(@Param("userAddr") UserAddr userAddr);

  /**
   * 删除地址
   *
   * @param addrId 地址id
   * @param userId 用户id
   */
  void deleteById(@Param("addrId") Long addrId, @Param("userId") Long userId);


  /**
   * 移除用户默认地址
   *
   * @param userId
   */
  void removeDefaultUserAddr(@Param("userId") Long userId);

  /**
   * 将地址设置为默认地址
   *
   * @param addrId 地址id
   * @param userId 用户id
   */
  void setDefaultUserAddr(@Param("addrId") Long addrId, @Param("userId") Long userId);

  /**
   * 用户地址的数量
   *
   * @param userId 用户id
   * @return 数量
   */
  int countByUserId(Long userId);

  /**
   * 通过用户id获取默认地址
   *
   * @param userId 用户id
   * @return 默认地址
   */
  UserAddrVO getUserDefaultAddrByUserId(@Param("userId") Long userId);

}
