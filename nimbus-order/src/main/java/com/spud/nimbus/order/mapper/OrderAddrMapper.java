package com.spud.nimbus.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spud.nimbus.order.model.OrderAddr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户订单配送地址 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Mapper
public interface OrderAddrMapper extends BaseMapper<OrderAddr> {
  /**
   * 获取用户订单配送地址列表
   *
   * @return 用户订单配送地址列表
   */
  List<OrderAddr> list();

  /**
   * 根据用户订单配送地址id获取用户订单配送地址
   *
   * @param orderAddrId 用户订单配送地址id
   * @return 用户订单配送地址
   */
  OrderAddr getByOrderAddrId(@Param("orderAddrId") Long orderAddrId);

  /**
   * 保存用户订单配送地址
   *
   * @param orderAddr 用户订单配送地址
   */
  void save(@Param("orderAddr") OrderAddr orderAddr);

  /**
   * 更新用户订单配送地址
   *
   * @param orderAddr 用户订单配送地址
   */
  void update(@Param("orderAddr") OrderAddr orderAddr);

  /**
   * 根据用户订单配送地址id删除用户订单配送地址
   *
   * @param orderAddrId
   */
  void deleteById(@Param("orderAddrId") Long orderAddrId);
}
