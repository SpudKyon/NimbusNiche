package com.spud.nimbus.search.listener;

import cn.throwx.canal.gule.CanalGlue;
import com.alibaba.fastjson2.JSON;
import com.spud.nimbus.common.rocketmq.config.RocketMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author spud
 * @date 2024/2/26
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = RocketMqConstant.CANAL_TOPIC, consumerGroup = RocketMqConstant.CANAL_TOPIC)
public class CanalListener implements RocketMQListener<String> {

	private final CanalGlue canalGlue;

	@Autowired
	public CanalListener(CanalGlue canalGlue) {
		this.canalGlue = canalGlue;
	}

	@Override
	public void onMessage(String message) {
		Map map = JSON.parseObject(message, Map.class);
		String table = map.get("table").toString();
		String database = map.get("database").toString();
		log.info("canal-database: {}, table: {}, mq message:{}", database, table, message);
		canalGlue.process(message);
	}

}
