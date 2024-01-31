package com.spud.nimbus.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.user.mapper.UserAddrMapper;
import com.spud.nimbus.user.model.UserAddr;
import com.spud.nimbus.user.service.UserAddrService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户地址 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-24
 */
@Service
public class UserAddrServiceImpl extends ServiceImpl<UserAddrMapper, UserAddr> implements UserAddrService {

}
