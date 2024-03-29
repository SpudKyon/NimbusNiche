package com.spud.nimbus.nimbus_order.dao;

import com.spud.nimbus.nimbus_order.model.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单项 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

}
