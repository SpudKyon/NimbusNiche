package com.spud.nimbus.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.util.PageUtil;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.order.mapper.OrderAddrMapper;
import com.spud.nimbus.order.service.OrderAddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spud.nimbus.order.model.OrderAddr;

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

  @Autowired
  private OrderAddrMapper orderAddrMapper;

  @Override
  public PageVO<OrderAddr> page(PageDTO pageDTO) {
    return PageUtil.doPage(pageDTO, () -> orderAddrMapper.list());
  }

  @Override
  public OrderAddr getByOrderAddrId(Long orderAddrId) {
    return orderAddrMapper.getByOrderAddrId(orderAddrId);
  }

  @Override
  public boolean save(OrderAddr orderAddr) {
    return super.save(orderAddr);
  }

  @Override
  public void update(OrderAddr orderAddr) {
    orderAddrMapper.update(orderAddr);
  }

  @Override
  public void deleteById(Long orderAddrId) {
    orderAddrMapper.deleteById(orderAddrId);
  }
}