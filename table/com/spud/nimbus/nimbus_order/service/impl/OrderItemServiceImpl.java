package com.spud.nimbus.nimbus_order.service.impl;

import com.spud.nimbus.nimbus_order.model.OrderItem;
import com.spud.nimbus.nimbus_order.dao.OrderItemMapper;
import com.spud.nimbus.nimbus_order.service.OrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单项 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}
