package com.spud.nimbus.order.listener;

import com.spud.nimbus.common.exception.NimbusException;
import com.spud.nimbus.common.order.bo.PayNotifyBO;
import com.spud.nimbus.common.response.ResultCode;
import com.spud.nimbus.common.rocketmq.config.RocketMqConstant;
import com.spud.nimbus.common.util.Json;
import com.spud.nimbus.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/25
 */
@Component
@Slf4j
@RocketMQMessageListener(topic = RocketMqConstant.ORDER_NOTIFY_TOPIC,consumerGroup = RocketMqConstant.ORDER_NOTIFY_TOPIC)
public class OrderNotifyConsumer implements RocketMQListener<PayNotifyBO> {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RocketMQTemplate orderNotifyStockTemplate;

    @Override
    public void onMessage(PayNotifyBO message) {
        log.info("订单回调开始... message: " + Json.toJsonString(message));
        orderService.updateByToPaySuccess(message.getOrderIds());
        // 发送消息，订单支付成功 通知库存扣减
        SendStatus sendStockStatus = orderNotifyStockTemplate.syncSend(RocketMqConstant.ORDER_NOTIFY_STOCK_TOPIC, new GenericMessage<>(message)).getSendStatus();
        if (!Objects.equals(sendStockStatus,SendStatus.SEND_OK)) {
            throw new NimbusException(ResultCode.EXCEPTION);
        }
    }
}
