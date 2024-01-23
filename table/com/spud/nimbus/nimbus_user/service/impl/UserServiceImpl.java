package com.spud.nimbus.nimbus_user.service.impl;

import com.spud.nimbus.nimbus_user.model.User;
import com.spud.nimbus.nimbus_user.dao.UserMapper;
import com.spud.nimbus.nimbus_user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
