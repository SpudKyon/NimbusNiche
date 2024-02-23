package com.spud.nimbus.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spud.nimbus.rbac.model.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户与角色对应关系 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

  /**
   * 根据用户id删除用户与角色关系
   *
   * @param userId
   */
  void deleteByUserId(Long userId);

  /**
   * 根据用户id 批量添加用户角色关系
   *
   * @param userId
   * @param roleIdList
   */
  void insertUserAndUserRole(@Param("userId") Long userId, @Param("roleIdList") List<Long> roleIdList);

  /**
   * 根据用户id 获取用户角色关系
   *
   * @param userId 用户id
   * @return 角色id列表
   */
  List<Long> getRoleIds(Long userId);

  /**
   * 根据角色id 删除用户角色关系
   *
   * @param roleId 用户id
   */
  void deleteByRoleId(Long roleId);
}
