package com.spud.nimbus.nimbus_auth.dao;

import com.spud.nimbus.nimbus_auth.model.AuthAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 统一账户信息 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Mapper
public interface AuthAccountMapper extends BaseMapper<AuthAccount> {

}
