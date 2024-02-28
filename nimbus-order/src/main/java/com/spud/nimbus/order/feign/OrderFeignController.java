package com.spud.nimbus.order.feign;

import cn.hutool.core.collection.CollectionUtil;
import com.spud.nimbus.api.order.bo.EsOrderBO;
import com.spud.nimbus.api.order.bo.OrderSimpleAmountInfoBO;
import com.spud.nimbus.api.order.bo.OrderStatusBO;
import com.spud.nimbus.api.order.constant.OrderStatus;
import com.spud.nimbus.api.order.feign.OrderFeignClient;
import com.spud.nimbus.api.order.vo.OrderAmountVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.response.ResultCode;
import com.spud.nimbus.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/25
 */
@RestController
public class OrderFeignController implements OrderFeignClient {

	@Autowired
	private OrderService orderService;

	@Override
	public Result<OrderAmountVO> getOrdersAmountAndIfNoCancel(List<Long> orderIds) {
		List<OrderStatusBO> orderStatus = orderService.getOrdersStatus(orderIds);
		if (CollectionUtil.isEmpty(orderStatus)) {
			return Result.fail(ResultCode.ORDER_NOT_EXIST, null);
		}

		for (OrderStatusBO statusBO : orderStatus) {
			// 订单已关闭
			if (statusBO.getStatus() == null || Objects.equals(statusBO.getStatus(), OrderStatus.CLOSE.value())) {
				return Result.fail(ResultCode.ORDER_EXPIRED, null);
			}
		}

		OrderAmountVO orderAmountVO = orderService.getOrdersActualAmount(orderIds);
		return Result.success(orderAmountVO);
	}

	@Override
	public Result<List<OrderStatusBO>> getOrdersStatus(List<Long> orderIds) {
		List<OrderStatusBO> orderStatusList = orderService.getOrdersStatus(orderIds);
		return Result.success(orderStatusList);
	}

	@Override
	public Result<List<OrderSimpleAmountInfoBO>> getOrdersSimpleAmountInfo(List<Long> orderIds) {
		return Result.success(orderService.getOrdersSimpleAmountInfo(orderIds));
	}

	@Override
	public Result<EsOrderBO> getEsOrder(Long orderId) {
		EsOrderBO esOrderBO = orderService.getEsOrder(orderId);
		return Result.success(esOrderBO);
	}

	@Override
	public Result<Void> updateOrderState(List<Long> orderIds) {
		return null;
	}

}
