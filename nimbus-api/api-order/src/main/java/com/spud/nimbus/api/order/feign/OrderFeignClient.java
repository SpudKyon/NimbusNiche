package com.spud.nimbus.api.order.feign;

import com.spud.nimbus.api.order.bo.EsOrderBO;
import com.spud.nimbus.api.order.bo.OrderSimpleAmountInfoBO;
import com.spud.nimbus.api.order.bo.OrderStatusBO;
import com.spud.nimbus.api.order.vo.OrderAmountVO;
import com.spud.nimbus.common.feign.FeignInsideAuthConfig;
import com.spud.nimbus.common.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/9
 */
@FeignClient(value = "nimbus-order",contextId = "order")
public interface OrderFeignClient {


  /**
   * 如果订单没有被取消的话，获取订单金额，否之会获取失败
   *
   * @param orderIds 订单id列表
   * @return 订单金额
   */
  @GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/ordgetOrdersAmountAndIfNoCanceler")
  Result<OrderAmountVO> getOrdersAmountAndIfNoCancel(@RequestParam("orderIds") List<Long> orderIds);

  /**
   * 获取订单状态，如果订单状态不存在，则说明订单没有创建
   *
   * @param orderIds 订单id列表
   * @return 订单状态
   */
  @GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/getOrdersStatus")
  Result<List<OrderStatusBO>> getOrdersStatus(@RequestParam("orderIds") List<Long> orderIds);

  /**
   * 获取订单中的金额信息
   *
   * @param orderIds 订单id列表
   * @return 订单中的金额信息
   */
  @GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/getOrdersSimpleAmountInfo")
  Result<List<OrderSimpleAmountInfoBO>> getOrdersSimpleAmountInfo(@RequestParam("orderIds") List<Long> orderIds);


  /**
   * 获取订单需要保存到es中的数据
   *
   * @param orderId 订单id
   * @return es中的数据
   */
  @GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/getEsOrder")
  Result<EsOrderBO> getEsOrder(@RequestParam("orderId")Long orderId);

  /**
   * 支付时更新订单状态
   * @param orderIds 订单id列表
   * @return null
   */
  @GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/updateOrderState")
  Result<Void> updateOrderState(@RequestParam("orderIds") List<Long> orderIds);
}