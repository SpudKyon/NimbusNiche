package com.spud.nimbus.nimbus_rbac.dao;

import com.spud.nimbus.nimbus_rbac.model.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
