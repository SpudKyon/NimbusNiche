package com.spud.nimbus.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spud.nimbus.rbac.model.Menu;
import com.spud.nimbus.rbac.vo.MenuSimpleVO;
import com.spud.nimbus.rbac.vo.MenuVO;

import java.util.List;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
public interface MenuService extends IService<Menu> {

	/**
	 * 根据菜单管理id获取菜单管理
	 * @param menuId 菜单管理id
	 * @return 菜单管理
	 */
	MenuVO getByMenuId(Long menuId);

	/**
	 * 保存菜单管理
	 * @param menu 菜单管理
	 * @return 是否保存成功
	 */
	boolean save(Menu menu);

	/**
	 * 更新菜单管理
	 * @param menu 菜单管理
	 */
	void update(Menu menu);

	/**
	 * 根据菜单管理id删除菜单管理
	 * @param menuId 菜单id
	 * @param sysType 系统类型
	 */
	void deleteById(Long menuId, Integer sysType);

	/**
	 * 根据系统类型获取该系统的菜单列表
	 * @param sysType 系统类型
	 * @return 菜单列表
	 */
	List<Menu> listBySysType(Integer sysType);

	/**
	 * 根据系统类型获取该系统的菜单列表 + 菜单下的权限列表
	 * @param sysType 系统类型
	 * @return 菜单列表 + 菜单下的权限列表
	 */
	List<MenuSimpleVO> listWithPermissions(Integer sysType);

	/**
	 * 获取当前用户可见的菜单ids
	 * @param userId 用户id
	 * @return 菜单列表
	 */
	List<Long> listMenuIds(Long userId);

}
