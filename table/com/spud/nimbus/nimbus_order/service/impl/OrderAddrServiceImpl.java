package com.spud.nimbus.nimbus_order.service.impl;

import com.spud.nimbus.nimbus_order.model.OrderAddr;
import com.spud.nimbus.nimbus_order.dao.OrderAddrMapper;
import com.spud.nimbus.nimbus_order.service.OrderAddrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户订单配送地址 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class OrderAddrServiceImpl extends ServiceImpl<OrderAddrMapper, OrderAddr> implements OrderAddrService {

}
