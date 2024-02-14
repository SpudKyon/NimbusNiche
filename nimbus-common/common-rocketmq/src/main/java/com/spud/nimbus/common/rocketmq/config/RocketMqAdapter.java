package com.spud.nimbus.common.rocketmq.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author spud
 * @date 2024/2/7
 */
@RefreshScope
@Slf4j
@Configuration
@Import({RocketMQAutoConfiguration.class})
public class RocketMqAdapter {

  @Autowired
  private RocketMQMessageConverter rocketMqMessageConverter;

  @Value("${rocketmq.name-server:}")
  private String nameServer;

  public RocketMQTemplate getTemplateByTopicName(String topic){
    RocketMQTemplate mqTemplate = new RocketMQTemplate();
    DefaultMQProducer producer = new DefaultMQProducer(topic);
    producer.setNamesrvAddr(nameServer);
    producer.setRetryTimesWhenSendFailed(2);
    producer.setSendMsgTimeout((int) RocketMqConstant.TIMEOUT);
    mqTemplate.setProducer(producer);
    mqTemplate.setMessageConverter(rocketMqMessageConverter.getMessageConverter());
    return mqTemplate;
  }
}
