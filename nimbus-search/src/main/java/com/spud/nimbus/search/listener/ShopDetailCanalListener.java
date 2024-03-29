package com.spud.nimbus.search.listener;

import cn.hutool.core.util.StrUtil;
import cn.throwx.canal.gule.model.CanalBinLogResult;
import cn.throwx.canal.gule.support.processor.BaseCanalBinlogEventProcessor;
import com.spud.nimbus.api.product.bo.EsProductBO;
import com.spud.nimbus.api.product.feign.ProductFeignClient;
import com.spud.nimbus.common.constant.StatusEnum;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.search.bo.ShopDetailBO;
import com.spud.nimbus.search.manager.ProductUpdateManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/26
 */
@Slf4j
@Component
public class ShopDetailCanalListener extends BaseCanalBinlogEventProcessor<ShopDetailBO> {

	private final ProductUpdateManager productUpdateManager;

	private final ProductFeignClient productFeignClient;

	@Autowired
	public ShopDetailCanalListener(ProductUpdateManager productUpdateManager, ProductFeignClient productFeignClient) {
		this.productUpdateManager = productUpdateManager;
		this.productFeignClient = productFeignClient;
	}

	/**
	 * 新增店铺
	 */
	@Override
	protected void processInsertInternal(CanalBinLogResult<ShopDetailBO> shopDetailResult) {

	}

	/**
	 * 更新店铺
	 * @param result
	 */
	@Override
	protected void processUpdateInternal(CanalBinLogResult<ShopDetailBO> result) {
		ShopDetailBO beforeData = result.getBeforeData();
		if (Objects.isNull(beforeData.getShopName()) && StrUtil.isBlank(beforeData.getShopLogo())
				&& !Objects.equals(beforeData.getShopStatus(), StatusEnum.ENABLE.value())) {
			return;
		}
		ShopDetailBO afterData = result.getAfterData();
		EsProductBO esProductBO = new EsProductBO();
		if (StrUtil.isNotBlank(beforeData.getShopName())) {
			esProductBO.setShopName(afterData.getShopName());
		}
		if (Objects.nonNull(beforeData.getShopLogo())) {
			esProductBO.setShopImg(afterData.getShopLogo());
		}
		if (Objects.nonNull(beforeData.getShopStatus())
				&& Objects.equals(beforeData.getShopId(), StatusEnum.ENABLE.value())) {
			esProductBO.setSpuStatus(StatusEnum.DISABLE.value());
		}
		Result<List<Long>> responseData = productFeignClient.getSpuIdsByShopId(afterData.getShopId());
		productUpdateManager.esUpdateSpuBySpuIds(responseData.getData(), esProductBO);
	}

}
