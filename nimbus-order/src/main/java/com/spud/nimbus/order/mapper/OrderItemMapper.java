package com.spud.nimbus.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spud.nimbus.order.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
  /**
   * 保存订单项
   *
   * @param orderItem 订单项
   */
  void save(@Param("orderItem") OrderItem orderItem);

  /**
   * 更新订单项
   *
   * @param orderItem 订单项
   */
  void update(@Param("orderItem") OrderItem orderItem);

  /**
   * 根据订单项id删除订单项
   *
   * @param orderItemId
   */
  void deleteById(@Param("orderItemId") Long orderItemId);

  /**
   * 批量保存
   *
   * @param orderItems 订单项列表
   */
  void saveBatch(@Param("orderItems") List<OrderItem> orderItems);

  /**
   * 根据订单号获取订单项
   *
   * @param orderId 订单id
   * @return 订单项
   */
  List<OrderItem> listOrderItemsByOrderId(@Param("orderId") Long orderId);

  /**
   * 根据订单id获取商品名称
   *
   * @param orderIdList 订单id
   * @return 商品名称列表
   */
  List<String> getSpuNameListByOrderIds(@Param("orderIdList") long[] orderIdList);

  /**
   * 根据订单id获取订单项数量
   *
   * @param orderId
   * @return
   */
  Integer countByOrderId(@Param("orderId") Long orderId);

}
