package com.spud.nimbus.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.user.mapper.UserMapper;
import com.spud.nimbus.user.model.User;
import com.spud.nimbus.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
