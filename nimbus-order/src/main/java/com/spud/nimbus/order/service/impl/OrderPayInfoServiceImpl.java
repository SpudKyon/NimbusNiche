package com.spud.nimbus.order.service.impl;

import com.spud.nimbus.order.mapper.OrderPayInfoMapper;
import com.spud.nimbus.order.model.OrderPayInfo;
import com.spud.nimbus.order.service.OrderPayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderPayInfoServiceImpl implements OrderPayInfoService {

    @Autowired
    private OrderPayInfoMapper orderPayInfoMapper;

    @Override
    public void save(OrderPayInfo orderPayInfo) {
        orderPayInfoMapper.save(orderPayInfo);
    }

    @Override
    public void update(OrderPayInfo orderPayInfo) {
        orderPayInfoMapper.update(orderPayInfo);
    }

    @Override
    public void deleteById(Long payId) {
        orderPayInfoMapper.deleteById(payId);
    }
}
