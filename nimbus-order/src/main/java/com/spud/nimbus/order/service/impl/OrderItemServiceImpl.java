package com.spud.nimbus.order.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.order.mapper.OrderItemMapper;
import com.spud.nimbus.order.model.OrderItem;
import com.spud.nimbus.order.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

	private final OrderItemMapper orderItemMapper;

	@Autowired
	public OrderItemServiceImpl(OrderItemMapper orderItemMapper) {
		this.orderItemMapper = orderItemMapper;
	}

	@Override
	public boolean save(OrderItem orderItem) {
		return super.save(orderItem);
	}

	@Override
	public void update(OrderItem orderItem) {
		orderItemMapper.update(orderItem);
	}

	@Override
	public void deleteById(Long orderItemId) {
		orderItemMapper.deleteById(orderItemId);
	}

	@Override
	public List<OrderItem> listOrderItemsByOrderId(Long orderId) {
		return orderItemMapper.listOrderItemsByOrderId(orderId);
	}

	@Override
	public void saveBatch(List<OrderItem> orderItems) {
		if (CollUtil.isEmpty(orderItems)) {
			return;
		}
		orderItemMapper.saveBatch(orderItems);
	}

	@Override
	public List<String> getSpuNameListByOrderIds(long[] orderIdList) {
		return orderItemMapper.getSpuNameListByOrderIds(orderIdList);
	}

	@Override
	public Integer countByOrderId(Long orderId) {
		return orderItemMapper.countByOrderId(orderId);
	}

}
