package com.spud.nimbus.product.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.api.order.bo.OrderStatusBO;
import com.spud.nimbus.api.order.constant.OrderStatus;
import com.spud.nimbus.api.order.feign.OrderFeignClient;
import com.spud.nimbus.api.product.dto.SkuStockLockDTO;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.util.PageUtil;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.common.exception.NimbusException;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.response.ResultCode;
import com.spud.nimbus.common.rocketmq.config.RocketMqConstant;
import com.spud.nimbus.product.bo.SkuWithStockBO;
import com.spud.nimbus.product.mapper.SkuStockLockMapper;
import com.spud.nimbus.product.mapper.SkuStockMapper;
import com.spud.nimbus.product.mapper.SpuExtensionMapper;
import com.spud.nimbus.product.model.SkuStockLock;
import com.spud.nimbus.product.service.SkuStockLockService;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 库存锁定信息 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class SkuStockLockServiceImpl extends ServiceImpl<SkuStockLockMapper, SkuStockLock>
		implements SkuStockLockService {

	private final SkuStockLockMapper skuStockLockMapper;

	private final SpuExtensionMapper spuExtensionMapper;

	private final SkuStockMapper skuStockMapper;

	private final OrderFeignClient orderFeignClient;

	private final RocketMQTemplate stockMqTemplate;

	@Autowired
	public SkuStockLockServiceImpl(SkuStockLockMapper skuStockLockMapper, SpuExtensionMapper spuExtensionMapper, SkuStockMapper skuStockMapper, OrderFeignClient orderFeignClient, RocketMQTemplate stockMqTemplate) {
		this.skuStockLockMapper = skuStockLockMapper;
		this.spuExtensionMapper = spuExtensionMapper;
		this.skuStockMapper = skuStockMapper;
		this.orderFeignClient = orderFeignClient;
		this.stockMqTemplate = stockMqTemplate;
	}

	@Override
	public PageVO<SkuStockLock> page(PageDTO pageDTO) {
		return PageUtil.doPage(pageDTO, () -> skuStockLockMapper.list());
	}

	@Override
	public SkuStockLock getById(Long id) {
		return skuStockLockMapper.getById(id);
	}

	@Override
	public boolean save(SkuStockLock skuStockLock) {
		return super.save(skuStockLock);
	}

	@Override
	public void update(SkuStockLock skuStockLock) {
		skuStockLockMapper.update(skuStockLock);
	}

	@Override
	public void deleteById(Long id) {
		skuStockLockMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result<Void> lock(List<SkuStockLockDTO> skuStockLocksParam) {

		List<SkuStockLock> skuStockLocks = new ArrayList<>();
		for (SkuStockLockDTO skuStockLockDTO : skuStockLocksParam) {
			SkuStockLock skuStockLock = new SkuStockLock();
			skuStockLock.setCount(skuStockLockDTO.getCount());
			skuStockLock.setOrderId(skuStockLockDTO.getOrderId());
			skuStockLock.setSkuId(skuStockLockDTO.getSkuId());
			skuStockLock.setSpuId(skuStockLockDTO.getSpuId());
			skuStockLock.setStatus(0);
			skuStockLocks.add(skuStockLock);
			// 减sku库存
			int skuStockUpdateIsSuccess = skuStockMapper.reduceStockByOrder(skuStockLockDTO.getSkuId(),
					skuStockLockDTO.getCount());
			if (skuStockUpdateIsSuccess < 1) {
				throw new NimbusException(ResultCode.NOT_STOCK, "商品skuId: " + skuStockLockDTO.getSkuId());
			}
			// 减商品库存
			int spuStockUpdateIsSuccess = spuExtensionMapper.reduceStockByOrder(skuStockLockDTO.getSpuId(),
					skuStockLockDTO.getCount());
			if (spuStockUpdateIsSuccess < 1) {
				throw new NimbusException(ResultCode.NOT_STOCK, "商品spuId: " + skuStockLockDTO.getSpuId());
			}
		}
		// 保存库存锁定信息
		skuStockLockMapper.saveBatch(skuStockLocks);
		List<Long> orderIds = skuStockLocksParam.stream().map(SkuStockLockDTO::getOrderId).collect(Collectors.toList());
		// 一个小时后解锁库存
		SendStatus sendStatus = stockMqTemplate.syncSend(RocketMqConstant.STOCK_UNLOCK_TOPIC,
				new GenericMessage<>(orderIds), RocketMqConstant.TIMEOUT, RocketMqConstant.CANCEL_ORDER_DELAY_LEVEL + 1)
				.getSendStatus();
		if (!Objects.equals(sendStatus, SendStatus.SEND_OK)) {
			// 消息发不出去就抛异常，发的出去无所谓
			throw new NimbusException(ResultCode.EXCEPTION);
		}
		return Result.success(null);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void unlockStock(List<Long> orderIds) {
		Result<List<OrderStatusBO>> ordersStatusResponse = orderFeignClient.getOrdersStatus(orderIds);
		if (!ordersStatusResponse.isSuccess()) {
			throw new NimbusException(ordersStatusResponse.getMsg());
		}
		List<OrderStatusBO> orderStatusList = ordersStatusResponse.getData();

		List<Long> needUnLockOrderId = new ArrayList<>();
		for (OrderStatusBO orderStatusBO : orderStatusList) {
			// 该订单没有下单成功，或订单已取消，赶紧解锁库存
			if (orderStatusBO.getStatus() == null
					|| Objects.equals(orderStatusBO.getStatus(), OrderStatus.CLOSE.value())) {
				needUnLockOrderId.add(orderStatusBO.getOrderId());
			}
		}

		if (CollectionUtil.isEmpty(needUnLockOrderId)) {
			return;
		}

		List<SkuWithStockBO> allSkuWithStocks = skuStockLockMapper.listByOrderIds(needUnLockOrderId);
		if (CollectionUtil.isEmpty(allSkuWithStocks)) {
			return;
		}
		List<Long> lockIds = allSkuWithStocks.stream().map(SkuWithStockBO::getId).collect(Collectors.toList());

		// 还原商品库存
		spuExtensionMapper.addStockByOrder(allSkuWithStocks);
		// 还原sku库存
		skuStockMapper.addStockByOrder(allSkuWithStocks);
		// 将锁定状态标记为已解锁
		skuStockLockMapper.unLockByIds(lockIds);

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void markerStockUse(List<Long> orderIds) {

		List<SkuWithStockBO> skuWithStocks = skuStockLockMapper.listByOrderIds(orderIds);

		// ==============订单从正常状态变成已支付=============
		if (CollectionUtil.isNotEmpty(skuWithStocks)) {
			// 减少商品实际库存，增加销量
			spuExtensionMapper.reduceActualStockByOrder(skuWithStocks);
			// 减少sku实际库存
			skuStockMapper.reduceActualStockByOrder(skuWithStocks);
		}

		// ================ 由于订单支付回调成功过慢，导致订单由取消变成已支付 ====================

		List<SkuWithStockBO> unLockSkuWithStocks = skuStockLockMapper.listUnLockByOrderIds(orderIds);

		if (CollectionUtil.isNotEmpty(unLockSkuWithStocks)) {
			// 减少商品实际库存，增加销量
			spuExtensionMapper.reduceActualStockByCancelOrder(unLockSkuWithStocks);
			// 减少sku实际库存
			skuStockMapper.reduceActualStockByCancelOrder(unLockSkuWithStocks);
		}
		// 将锁定状态标记为已使用
		skuStockLockMapper.markerStockUse(orderIds);
	}

}
