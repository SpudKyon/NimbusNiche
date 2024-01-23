package com.spud.nimbus.nimbus_nacos.dao;

import com.spud.nimbus.nimbus_nacos.model.GroupCapacity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 集群、各Group容量信息表 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Mapper
public interface GroupCapacityMapper extends BaseMapper<GroupCapacity> {

}
