package com.spud.nimbus.search.listener;

import cn.hutool.core.util.StrUtil;
import cn.throwx.canal.gule.model.CanalBinLogResult;
import cn.throwx.canal.gule.support.processor.BaseCanalBinlogEventProcessor;
import com.spud.nimbus.api.product.bo.EsProductBO;
import com.spud.nimbus.api.product.feign.ProductFeignClient;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.search.bo.BrandBO;
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
public class BrandCanalListener extends BaseCanalBinlogEventProcessor<BrandBO> {

	private final ProductUpdateManager productUpdateManager;

	private final ProductFeignClient productFeignClient;

	@Autowired
	public BrandCanalListener(ProductUpdateManager productUpdateManager, ProductFeignClient productFeignClient) {
		this.productUpdateManager = productUpdateManager;
		this.productFeignClient = productFeignClient;
	}

	/**
	 * 新增品牌
	 */
	@Override
	protected void processInsertInternal(CanalBinLogResult<BrandBO> brandResult) {

	}

	/**
	 * 更新品牌
	 * @param result
	 */
	@Override
	protected void processUpdateInternal(CanalBinLogResult<BrandBO> result) {
		BrandBO beforeData = result.getBeforeData();
		if (Objects.isNull(beforeData.getName()) && StrUtil.isBlank(beforeData.getImgUrl())) {
			return;
		}
		BrandBO afterData = result.getAfterData();
		EsProductBO esProductBO = new EsProductBO();
		if (StrUtil.isNotBlank(beforeData.getName())) {
			esProductBO.setBrandName(afterData.getName());
		}
		if (Objects.nonNull(beforeData.getImgUrl())) {
			esProductBO.setBrandImg(afterData.getImgUrl());
		}
		Result<List<Long>> responseData = productFeignClient.getSpuIdsByBrandId(afterData.getBrandId());
		productUpdateManager.esUpdateSpuBySpuIds(responseData.getData(), esProductBO);
	}

}
