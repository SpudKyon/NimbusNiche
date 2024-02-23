package com.spud.nimbus.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spud.nimbus.api.rbac.bo.UriPermissionBO;
import com.spud.nimbus.rbac.model.MenuPermission;
import com.spud.nimbus.rbac.vo.MenuPermissionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单资源 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Mapper
public interface MenuPermissionMapper extends BaseMapper<MenuPermission> {
  /**
   * 获取菜单资源列表
   *
   * @param sysType 系统类型
   * @return 菜单资源列表
   */
  List<MenuPermissionVO> list(@Param("sysType") Integer sysType);

  /**
   * 根据菜单资源id获取菜单资源
   *
   * @param menuPermissionId 菜单资源id
   * @return 菜单资源
   */
  MenuPermissionVO getByMenuPermissionId(@Param("menuPermissionId") Long menuPermissionId);

  /**
   * 保存菜单资源
   *
   * @param menuPermission 菜单资源
   */
  void save(@Param("menuPermission") MenuPermission menuPermission);

  /**
   * 更新菜单资源
   *
   * @param menuPermission 菜单资源
   */
  void update(@Param("menuPermission") MenuPermission menuPermission);

  /**
   * 根据菜单资源id删除菜单资源
   *
   * @param menuPermissionId
   * @param sysType
   */
  void deleteById(@Param("menuPermissionId") Long menuPermissionId, @Param("sysType") Integer sysType);

  /**
   * 获取某个类型用户的所有权限列表
   *
   * @param sysType 系统类型
   * @return 权限列表
   */
  List<String> listAllPermissionBySysType(@Param("sysType") Integer sysType);

  /**
   * 获取某个用户的权限列表
   *
   * @param userId  用户id
   * @param sysType 系统类型
   * @return 权限列表
   */
  List<String> listPermissionByUserIdAndSysType(@Param("userId") Long userId, @Param("sysType") Integer sysType);

  /**
   * 根据系统类型，获取该类型用户拥有的所有权限数据
   *
   * @param sysType 系统类型
   * @return uri权限列表
   */
  List<UriPermissionBO> listUriPermissionInfo(@Param("sysType") Integer sysType);

  /**
   * 根据menuId获取菜单资源列表
   *
   * @param menuId 菜单id
   * @return 菜单资源列表数据
   */
  List<MenuPermissionVO> listByMenuId(@Param("menuId") Long menuId);

  /**
   * 通过权限对应的编码获取权限信息
   *
   * @param permission 权限对应的编码
   * @param sysType    系统类型
   * @return
   */
  MenuPermission getByPermission(@Param("permission") String permission, @Param("sysType") Integer sysType);
}
