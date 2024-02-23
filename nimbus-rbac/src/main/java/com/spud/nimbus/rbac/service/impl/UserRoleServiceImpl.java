package com.spud.nimbus.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.rbac.mapper.UserRoleMapper;
import com.spud.nimbus.rbac.model.UserRole;
import com.spud.nimbus.rbac.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
