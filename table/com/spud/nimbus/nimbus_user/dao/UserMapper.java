package com.spud.nimbus.nimbus_user.dao;

import com.spud.nimbus.nimbus_user.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
