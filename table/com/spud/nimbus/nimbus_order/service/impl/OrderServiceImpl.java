package com.spud.nimbus.nimbus_order.service.impl;

import com.spud.nimbus.nimbus_order.model.Order;
import com.spud.nimbus.nimbus_order.dao.OrderMapper;
import com.spud.nimbus.nimbus_order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单信息 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
