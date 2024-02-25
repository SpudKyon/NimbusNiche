package com.spud.nimbus.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spud.nimbus.order.model.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单项 服务类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
public interface OrderItemService extends IService<OrderItem> {
  /**
   * 保存订单项
   *
   * @param orderItem 订单项
   */
  boolean save(OrderItem orderItem);

  /**
   * 更新订单项
   *
   * @param orderItem 订单项
   */
  void update(OrderItem orderItem);

  /**
   * 根据订单项id删除订单项
   *
   * @param orderItemId
   */
  void deleteById(Long orderItemId);

  /**
   * 根据订单号获取订单项
   *
   * @param orderId 订单id
   * @return 订单项
   */
  List<OrderItem> listOrderItemsByOrderId(Long orderId);

  /**
   * 批量保存订单项信息
   *
   * @param orderItems
   */
  void saveBatch(List<OrderItem> orderItems);

  /**
   * 根据订单id获取商品名称
   *
   * @param orderIdList 订单id
   * @return 商品名称列表
   */
  List<String> getSpuNameListByOrderIds(long[] orderIdList);

  /**
   * 根据订单id获取订单项数量
   *
   * @param orderId
   * @return
   */
  Integer countByOrderId(@Param("orderId") Long orderId);
}
