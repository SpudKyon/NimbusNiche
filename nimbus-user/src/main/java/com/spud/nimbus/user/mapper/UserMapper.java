package com.spud.nimbus.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spud.nimbus.user.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
