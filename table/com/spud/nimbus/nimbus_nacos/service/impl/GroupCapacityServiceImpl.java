package com.spud.nimbus.nimbus_nacos.service.impl;

import com.spud.nimbus.nimbus_nacos.model.GroupCapacity;
import com.spud.nimbus.nimbus_nacos.dao.GroupCapacityMapper;
import com.spud.nimbus.nimbus_nacos.service.GroupCapacityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 集群、各Group容量信息表 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class GroupCapacityServiceImpl extends ServiceImpl<GroupCapacityMapper, GroupCapacity> implements GroupCapacityService {

}
