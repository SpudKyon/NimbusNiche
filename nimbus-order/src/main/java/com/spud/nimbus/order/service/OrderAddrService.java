package com.spud.nimbus.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.order.model.OrderAddr;

/**
 * <p>
 * 用户订单配送地址 服务类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
public interface OrderAddrService extends IService<OrderAddr> {
  /**
   * 分页获取用户订单配送地址列表
   *
   * @param pageDTO 分页参数
   * @return 用户订单配送地址列表分页数据
   */
  PageVO<OrderAddr> page(PageDTO pageDTO);

  /**
   * 根据用户订单配送地址id获取用户订单配送地址
   *
   * @param orderAddrId 用户订单配送地址id
   * @return 用户订单配送地址
   */
  OrderAddr getByOrderAddrId(Long orderAddrId);

  /**
   * 保存用户订单配送地址
   *
   * @param orderAddr 用户订单配送地址
   */
  boolean save(OrderAddr orderAddr);

  /**
   * 更新用户订单配送地址
   *
   * @param orderAddr 用户订单配送地址
   */
  void update(OrderAddr orderAddr);

  /**
   * 根据用户订单配送地址id删除用户订单配送地址
   *
   * @param orderAddrId
   */
  void deleteById(Long orderAddrId);
}