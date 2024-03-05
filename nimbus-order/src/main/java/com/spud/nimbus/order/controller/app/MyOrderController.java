package com.spud.nimbus.order.controller.app;

import com.spud.nimbus.api.order.constant.OrderStatus;
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
import com.spud.nimbus.order.model.OrderItem;
import com.spud.nimbus.order.service.OrderAddrService;
import com.spud.nimbus.order.service.OrderItemService;
import com.spud.nimbus.order.service.OrderService;
import com.spud.nimbus.order.vo.OrderAddrVO;
import com.spud.nimbus.order.vo.OrderCountVO;
import com.spud.nimbus.order.vo.OrderItemVO;
import com.spud.nimbus.order.vo.OrderShopVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/28
 */
@RestController
@RequestMapping("/p/myOrder")
@Tag(name = "app-我的订单接口")
public class MyOrderController {

    private final OrderService orderService;

    private final OrderItemService orderItemService;

    private final SearchOrderFeignClient searchOrderFeignClient;

    private final OrderAddrService orderAddrService;

    @Autowired
    public MyOrderController(OrderService orderService, OrderItemService orderItemService, SearchOrderFeignClient searchOrderFeignClient, OrderAddrService orderAddrService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.searchOrderFeignClient = searchOrderFeignClient;
        this.orderAddrService = orderAddrService;
    }

    /**
     * 订单详情信息接口
     */
    @GetMapping("/order_detail")
    @Operation(summary = "订单详情信息", description = "根据订单号获取订单详情信息")
    @Parameter(name = "orderId", description = "订单号", required = true)
    public Result<OrderShopVO> orderDetail(@RequestParam(value = "orderId") Long orderId) {
        Long userId = AuthUserContext.get().getUserId();
        OrderShopVO orderShopDto = new OrderShopVO();
        Order order = orderService.getOrderByOrderIdAndUserId(orderId, userId);
        OrderAddr orderAddr = orderAddrService.getByOrderAddrId(order.getOrderAddrId());
        List<OrderItem> orderItems = orderItemService.listOrderItemsByOrderId(orderId);
        orderShopDto.setShopId(order.getShopId());
        orderShopDto.setDeliveryType(order.getDeliveryType());
        orderShopDto.setShopName(order.getShopName());
        orderShopDto.setCreateTime(order.getCreateTime());
        orderShopDto.setStatus(order.getStatus());
        orderShopDto.setOrderAddr(BeanUtil.map(orderAddr, OrderAddrVO.class));
        // 付款时间
        orderShopDto.setPayTime(order.getPayTime());
        // 发货时间
        orderShopDto.setDeliveryTime(order.getDeliveryTime());
        // 完成时间
        orderShopDto.setFinallyTime(order.getFinallyTime());
        // 取消时间
        orderShopDto.setCancelTime(order.getCancelTime());
        // 更新时间
        orderShopDto.setUpdateTime(order.getUpdateTime());
        orderShopDto.setOrderItems(BeanUtil.mapAsList(orderItems, OrderItemVO.class));
        orderShopDto.setTotal(order.getTotal());
        orderShopDto.setTotalNum(order.getAllCount());

        return Result.success(orderShopDto);
    }

    @GetMapping("/order_count")
    @Operation(summary = "计算各个订单数量", description = "根据订单状态计算各个订单数量")
    public Result<OrderCountVO> orderCount() {
        Long userId = AuthUserContext.get().getUserId();
        OrderCountVO orderCount = orderService.countNumberOfStatus(userId);
        return Result.success(orderCount);
    }

    /**
     * 分页获取
     */
    @GetMapping("/search_order")
    @Operation(summary = "订单列表信息查询", description = "根据订单编号或者订单中商品名称搜索")
    public Result<EsPageVO<EsOrderVO>> searchOrder(OrderSearchDTO orderSearchDTO) {
        Long userId = AuthUserContext.get().getUserId();
        orderSearchDTO.setUserId(userId);
        return searchOrderFeignClient.getOrderPage(orderSearchDTO);
    }

    /**
     * 取消订单
     */
    @PutMapping("/cancel/{orderId}")
    @Operation(summary = "根据订单号取消订单", description = "根据订单号取消订单")
    @Parameter(name = "orderId", description = "订单号", required = true)
    public Result<String> cancel(@PathVariable("orderId") Long orderId) {
        Long userId = AuthUserContext.get().getUserId();
        Order order = orderService.getOrderByOrderIdAndUserId(orderId, userId);
        if (!Objects.equals(order.getStatus(), OrderStatus.UNPAY.value())) {
            // 订单已支付，无法取消订单
            return Result.fail(ResultCode.ORDER_PAYED, null);
        }
        // 如果订单未支付的话，将订单设为取消状态
        orderService.cancelOrderAndGetCancelOrderIds(Collections.singletonList(order.getOrderId()));
        return Result.success(null);
    }

    /**
     * 确认收货
     */
    @PutMapping("/receipt/{orderId}")
    @Operation(summary = "根据订单号确认收货", description = "根据订单号确认收货")
    public Result<String> receipt(@PathVariable("orderId") Long orderId) {
        Long userId = AuthUserContext.get().getUserId();
        Order order = orderService.getOrderByOrderIdAndUserId(orderId, userId);
        if (!Objects.equals(order.getStatus(), OrderStatus.CONSIGNMENT.value())) {
            // 订单未发货，无法确认收货
            return Result.fail(ResultCode.ORDER_NO_DELIVERY, null);
        }
        List<OrderItem> orderItems = orderItemService.listOrderItemsByOrderId(orderId);
        order.setOrderItems(orderItems);
        // 确认收货
        orderService.receiptOrder(order.getOrderId());
        return Result.success(null);
    }

    /**
     * 删除订单
     */
    @DeleteMapping("/{orderId}")
    @Operation(summary = "根据订单号删除订单", description = "根据订单号删除订单")
    @Parameter(name = "orderId", description = "订单号", required = true)
    public Result<String> delete(@PathVariable("orderId") Long orderId) {
        Long userId = AuthUserContext.get().getUserId();
        Order order = orderService.getOrderByOrderIdAndUserId(orderId, userId);
        if (!Objects.equals(order.getStatus(), OrderStatus.SUCCESS.value())
                && !Objects.equals(order.getStatus(), OrderStatus.CLOSE.value())) {
            // 订单未完成或未关闭，无法删除订单
            return Result.fail(ResultCode.ORDER_NOT_FINISH_OR_CLOSE, null);
        }
        // 删除订单
        orderService.deleteOrder(order.getOrderId());
        return Result.success(null);
    }

}
