package com.spud.nimbus.payment.config;

import com.spud.nimbus.common.rocketmq.config.RocketMqAdapter;
import com.spud.nimbus.common.rocketmq.config.RocketMqConstant;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author spud
 * @date 2024/2/26
 */
@RefreshScope
@Configuration
public class RocketMqConfig {

  @Autowired
  private RocketMqAdapter rocketMqAdapter;

  @Lazy
  @Bean(destroyMethod = "destroy")
  public RocketMQTemplate orderNotifyTemplate() {
    return rocketMqAdapter.getTemplateByTopicName(RocketMqConstant.ORDER_NOTIFY_TOPIC);
  }
}
