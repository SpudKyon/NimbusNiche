package com.spud.nimbus.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.rbac.mapper.RoleMenuMapper;
import com.spud.nimbus.rbac.model.RoleMenu;
import com.spud.nimbus.rbac.service.RoleMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与菜单对应关系 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}
