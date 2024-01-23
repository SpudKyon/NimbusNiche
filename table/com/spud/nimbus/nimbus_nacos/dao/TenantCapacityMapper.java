package com.spud.nimbus.nimbus_nacos.dao;

import com.spud.nimbus.nimbus_nacos.model.TenantCapacity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 租户容量信息表 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Mapper
public interface TenantCapacityMapper extends BaseMapper<TenantCapacity> {

}
