package com.spud.nimbus.nimbus_auth.service.impl;

import com.spud.nimbus.nimbus_auth.model.AuthAccount;
import com.spud.nimbus.nimbus_auth.dao.AuthAccountMapper;
import com.spud.nimbus.nimbus_auth.service.AuthAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 统一账户信息 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class AuthAccountServiceImpl extends ServiceImpl<AuthAccountMapper, AuthAccount> implements AuthAccountService {

}
