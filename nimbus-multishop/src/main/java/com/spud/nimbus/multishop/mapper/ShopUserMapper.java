package com.spud.nimbus.multishop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spud.nimbus.multishop.model.ShopUser;
import com.spud.nimbus.multishop.vo.ShopUserSimpleVO;
import com.spud.nimbus.multishop.vo.ShopUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商家用户 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Mapper
public interface ShopUserMapper extends BaseMapper<ShopUser> {
  /**
   * 根据用户id获取当前登陆的商家用户信息
   *
   * @param userId 用户id
   * @return 商家用户信息
   */
  ShopUserSimpleVO getSimpleByUserId(@Param("userId") Long userId);

  /**
   * 获取店铺用户列表
   *
   * @param shopId   店铺id
   * @param nickName 昵称
   * @return 店铺用户列表
   */
  List<ShopUserVO> listByShopId(@Param("shopId") Long shopId, @Param("nickName") String nickName);

  /**
   * 根据用户id获取商家用户信息
   *
   * @param userId 用户id
   * @return 商家用户信息
   */
  ShopUserVO getByUserId(@Param("userId") Long userId);

  /**
   * 保存商家用户信息
   *
   * @param shopUser
   */
  void save(@Param("shopUser") ShopUser shopUser);

  /**
   * 更新店铺用户信息
   *
   * @param shopUser
   */
  void update(@Param("shopUser") ShopUser shopUser);

  /**
   * 根据店铺用户id删除店铺用户
   *
   * @param shopUserId
   */
  void deleteById(@Param("shopUserId") Long shopUserId);

  /**
   * 获取店主账号的用户id-第一个创建的账号（仅用于审核店铺）
   *
   * @param shopId
   * @return
   */
  Long getUserIdByShopId(@Param("shopId") Long shopId);

}