package com.spud.nimbus.nimbus_nacos.service.impl;

import com.spud.nimbus.nimbus_nacos.model.Users;
import com.spud.nimbus.nimbus_nacos.dao.UsersMapper;
import com.spud.nimbus.nimbus_nacos.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

}
