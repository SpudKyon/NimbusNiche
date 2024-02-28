package com.spud.nimbus.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.rbac.model.Role;
import com.spud.nimbus.rbac.vo.RoleVO;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
public interface RoleService extends IService<Role> {

	/**
	 * 分页获取角色列表
	 * @param pageDTO 分页参数
	 * @param sysType
	 * @param tenantId
	 * @return 角色列表分页数据
	 */
	PageVO<RoleVO> page(PageDTO pageDTO, Integer sysType, Long tenantId);

	/**
	 * 分页获取角色列表
	 * @param sysType 系统类型
	 * @param tenantId 租户id
	 * @return 角色列表分页数据
	 */
	List<RoleVO> list(Integer sysType, Long tenantId);

	/**
	 * 根据角色id获取角色
	 * @param roleId 角色id
	 * @return 角色
	 */
	RoleVO getByRoleId(Long roleId);

	/**
	 * 保存角色
	 * @param role 角色
	 * @param menuIds 菜单id列表
	 * @param menuPermissionIds 权限id列表
	 */
	void save(Role role, List<Long> menuIds, List<Long> menuPermissionIds);

	/**
	 * 更新角色
	 * @param role 角色
	 * @param menuIds 菜单id列表
	 * @param menuPermissionIds 权限id列表
	 */
	void update(Role role, List<Long> menuIds, List<Long> menuPermissionIds);

	/**
	 * 根据角色id删除角色
	 * @param roleId
	 * @param sysType
	 */
	void deleteById(Long roleId, Integer sysType);

	/**
	 * 根据角色id获取该角色所在系统
	 * @param roleId 角色id
	 * @return sysType
	 */
	Integer getBizType(Long roleId);

}
