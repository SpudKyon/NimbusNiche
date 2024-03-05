package com.spud.nimbus.order.controller.multishop;

import com.spud.nimbus.api.order.constant.OrderStatus;
import com.spud.nimbus.api.order.dto.DeliveryOrderDTO;
import com.spud.nimbus.api.search.feign.SearchOrderFeignClient;
import com.spud.nimbus.api.search.vo.EsPageVO;
import com.spud.nimbus.api.search.vo.search.EsOrderVO;
import com.spud.nimbus.common.dto.OrderSearchDTO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.response.ResultCode;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.common.util.BeanUtil;
import com.spud.nimbus.order.model.Order;
import com.spud.nimbus.order.model.OrderAddr;
import com.spud.nimbus.order.service.OrderAddrService;
import com.spud.nimbus.order.service.OrderService;
import com.spud.nimbus.order.vo.OrderAddrVO;
import com.spud.nimbus.order.vo.OrderVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/28
 */
@RestController("multishopOrderController")
@Controller
@RequestMapping("/m/order")
@Tag(name = "multishop-订单接口")
public class OrderController {

	private final OrderService orderService;

	private final SearchOrderFeignClient searchOrderFeignClient;

	private final OrderAddrService orderAddrService;

	@Autowired
	public OrderController(OrderService orderService, SearchOrderFeignClient searchOrderFeignClient, OrderAddrService orderAddrService) {
		this.orderService = orderService;
		this.searchOrderFeignClient = searchOrderFeignClient;
		this.orderAddrService = orderAddrService;
	}

	/**
	 * 分页获取
	 */
	@GetMapping("/page")
	@Operation(summary = "分页获取订单详情")
	public Result<EsPageVO<EsOrderVO>> page(OrderSearchDTO orderSearchDTO) {
		Long shopId = AuthUserContext.get().getTenantId();
		orderSearchDTO.setShopId(shopId);
		return searchOrderFeignClient.getOrderPage(orderSearchDTO);
	}

	/**
	 * 获取信息
	 */
	@GetMapping("/order_info/{orderId}")
	@Operation(summary = "根据id获取订单详情")
	public Result<OrderVO> info(@PathVariable("orderId") Long orderId) {
		// 订单和订单项
		Order order = orderService.getOrderAndOrderItemData(orderId, AuthUserContext.get().getTenantId());
		// 详情用户收货地址
		OrderAddr orderAddr = orderAddrService.getByOrderAddrId(order.getOrderAddrId());
		order.setOrderAddr(BeanUtil.map(orderAddr, OrderAddr.class));
		OrderVO orderVO = BeanUtil.map(order, OrderVO.class);
		return Result.success(orderVO);
	}

	/**
	 * 获取订单用户下单地址
	 */
	@GetMapping("/order_addr/{orderAddrId}")
	@Operation(summary = "获取订单用户下单地址")
	public Result<OrderAddrVO> getOrderAddr(@PathVariable("orderAddrId") Long orderAddrId) {
		OrderAddr orderAddr = orderAddrService.getByOrderAddrId(orderAddrId);
		return Result.success(BeanUtil.map(orderAddr, OrderAddrVO.class));
	}

	/**
	 * 订单项待发货数量查询
	 */
	@GetMapping("/order_item_and_address/{orderId}")
	@Operation(summary = "订单项待发货数量查询")
	public Result<OrderVO> getOrderItemAndAddress(@PathVariable("orderId") Long orderId) {
		// 订单和订单项
		Order order = orderService.getOrderAndOrderItemData(orderId, AuthUserContext.get().getTenantId());
		OrderVO orderVO = BeanUtil.map(order, OrderVO.class);
		// 用户收货地址
		OrderAddr orderAddr = orderAddrService.getByOrderAddrId(order.getOrderAddrId());
		orderVO.setOrderAddr(BeanUtil.map(orderAddr, OrderAddrVO.class));
		return Result.success(orderVO);
	}

	/**
	 * 发货
	 */
	@PostMapping("/delivery")
	@Operation(summary = "发货")
	public Result<Void> delivery(@Valid @RequestBody DeliveryOrderDTO deliveryOrderParam) {
		OrderVO order = orderService.getOrderByOrderId(deliveryOrderParam.getOrderId());
		// 订单不在支付状态
		if (!Objects.equals(order.getStatus(), OrderStatus.PADYED.value())) {
			return Result.fail(ResultCode.ORDER_NOT_PAYED, null);
		}
		orderService.delivery(deliveryOrderParam);
		return Result.success(null);
	}

}
