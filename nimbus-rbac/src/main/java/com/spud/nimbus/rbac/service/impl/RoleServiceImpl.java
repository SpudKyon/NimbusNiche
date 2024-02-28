package com.spud.nimbus.rbac.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.util.PageUtil;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.rbac.mapper.RoleMapper;
import com.spud.nimbus.rbac.mapper.RoleMenuMapper;
import com.spud.nimbus.rbac.mapper.UserRoleMapper;
import com.spud.nimbus.rbac.model.Role;
import com.spud.nimbus.rbac.model.RoleMenu;
import com.spud.nimbus.rbac.service.RoleService;
import com.spud.nimbus.rbac.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private RoleMenuMapper roleMenuMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public PageVO<RoleVO> page(PageDTO pageDTO, Integer sysType, Long tenantId) {
		return PageUtil.doPage(pageDTO, () -> roleMapper.list(sysType, tenantId));
	}

	@Override
	public List<RoleVO> list(Integer sysType, Long tenantId) {
		return roleMapper.list(sysType, tenantId);
	}

	@Override
	public RoleVO getByRoleId(Long roleId) {
		RoleVO role = roleMapper.getByRoleId(roleId);
		List<RoleMenu> roleMenus = roleMenuMapper.getByRoleId(roleId);
		role.setMenuIds(roleMenus.stream().filter(roleMenu -> roleMenu.getMenuId() != null).map(RoleMenu::getMenuId)
				.collect(Collectors.toList()));
		role.setMenuPermissionIds(roleMenus.stream().filter(roleMenu -> roleMenu.getMenuPermissionId() != null)
				.map(RoleMenu::getMenuPermissionId).collect(Collectors.toList()));
		return role;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(Role role, List<Long> menuIds, List<Long> menuPermissionIds) {
		roleMapper.save(role);
		insertMenuAndPermission(role.getRoleId(), menuIds, menuPermissionIds);
	}

	@Override
	public void update(Role role, List<Long> menuIds, List<Long> menuPermissionIds) {
		roleMapper.update(role);
		roleMenuMapper.deleteByRoleId(role.getRoleId());
		insertMenuAndPermission(role.getRoleId(), menuIds, menuPermissionIds);
	}

	private void insertMenuAndPermission(Long roleId, List<Long> menuIds, List<Long> menuPermissionIds) {
		if (CollectionUtil.isNotEmpty(menuIds)) {
			List<RoleMenu> roleMenus = menuIds.stream().map(menuId -> {
				RoleMenu roleMenu = new RoleMenu();
				roleMenu.setRoleId(roleId);
				roleMenu.setMenuId(menuId);
				return roleMenu;
			}).collect(Collectors.toList());
			roleMenuMapper.insertBatch(roleMenus);
		}

		if (CollectionUtil.isNotEmpty(menuPermissionIds)) {
			List<RoleMenu> roleMenus = menuPermissionIds.stream().map(menuPermissionId -> {
				RoleMenu roleMenu = new RoleMenu();
				roleMenu.setRoleId(roleId);
				roleMenu.setMenuPermissionId(menuPermissionId);
				return roleMenu;
			}).collect(Collectors.toList());
			roleMenuMapper.insertBatch(roleMenus);
		}
	}

	@Override
	public void deleteById(Long roleId, Integer sysType) {
		roleMapper.deleteById(roleId, sysType);
		roleMenuMapper.deleteByRoleId(roleId);
		userRoleMapper.deleteByRoleId(roleId);
	}

	@Override
	public Integer getBizType(Long roleId) {
		return roleMapper.getBizType(roleId);
	}

}
