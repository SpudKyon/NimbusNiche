package com.spud.nimbus.search.listener;

import cn.hutool.core.util.StrUtil;
import cn.throwx.canal.gule.model.CanalBinLogResult;
import cn.throwx.canal.gule.support.processor.BaseCanalBinlogEventProcessor;
import com.spud.nimbus.api.product.bo.EsProductBO;
import com.spud.nimbus.api.product.constant.CategoryLevel;
import com.spud.nimbus.api.product.feign.CategoryFeignClient;
import com.spud.nimbus.api.product.feign.ProductFeignClient;
import com.spud.nimbus.common.constant.Constant;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.search.bo.CategoryBO;
import com.spud.nimbus.search.manager.ProductUpdateManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/26
 */
@Slf4j
@Component
public class CategoryCanalListener extends BaseCanalBinlogEventProcessor<CategoryBO> {

	private final CategoryFeignClient categoryFeignClient;

	private final ProductUpdateManager productUpdateManager;

	private final ProductFeignClient productFeignClient;

	@Autowired
	public CategoryCanalListener(CategoryFeignClient categoryFeignClient, ProductUpdateManager productUpdateManager, ProductFeignClient productFeignClient) {
		this.categoryFeignClient = categoryFeignClient;
		this.productUpdateManager = productUpdateManager;
		this.productFeignClient = productFeignClient;
	}

	/**
	 * 插入商品，此时插入es
	 */
	@Override
	protected void processInsertInternal(CanalBinLogResult<CategoryBO> categoryBo) {

	}

	/**
	 * 更新分类，删除商品索引，再重新构建一个
	 * @param result
	 */
	@Override
	protected void processUpdateInternal(CanalBinLogResult<CategoryBO> result) {
		CategoryBO beforeData = result.getBeforeData();
		if (Objects.isNull(beforeData.getName()) && Objects.isNull(beforeData.getStatus())) {
			return;
		}
		CategoryBO afterData = result.getAfterData();
		EsProductBO esProductBO = new EsProductBO();
		if (StrUtil.isNotBlank(beforeData.getName())) {
			insertCategoryName(esProductBO, afterData);
		}
		// 更新分类列表下商品的状态
		if (Objects.nonNull(beforeData.getStatus())) {
			esProductBO.setSpuStatus(beforeData.getStatus());
		}
		List<Long> spuIds = getSpuIdsByCategoryId(afterData);
		productUpdateManager.esUpdateSpuBySpuIds(spuIds, esProductBO);
	}

	/**
	 * 插入需要修改的分类名称
	 * @param esProductBO
	 * @param afterData
	 */
	private void insertCategoryName(EsProductBO esProductBO, CategoryBO afterData) {
		// 平台分类
		if (Objects.equals(Constant.PLATFORM_SHOP_ID, afterData.getShopId())) {
			if (afterData.getLevel().equals(CategoryLevel.First.value())) {
				esProductBO.setPrimaryCategoryName(afterData.getName());
			}
			else if (afterData.getLevel().equals(CategoryLevel.SECOND.value())) {
				esProductBO.setSecondaryCategoryName(afterData.getName());
			}
			else {
				esProductBO.setCategoryName(afterData.getName());
			}
		}
		// 商家分类
		else {
			if (afterData.getLevel().equals(CategoryLevel.First.value())) {
				esProductBO.setShopPrimaryCategoryName(afterData.getName());
			}
			else if (afterData.getLevel().equals(CategoryLevel.SECOND.value())) {
				esProductBO.setShopSecondaryCategoryName(afterData.getName());
			}
		}

	}

	/**
	 * 根据分类信息，获取分类下的商品Id列表
	 * @param category
	 * @return
	 */
	private List<Long> getSpuIdsByCategoryId(CategoryBO category) {
		List<Long> spuIds = new ArrayList<>();
		Result<List<Long>> spuIdResponse = null;
		List<Long> categoryIds = new ArrayList<>();
		Boolean isSearch = (category.getShopId().equals(Constant.PLATFORM_SHOP_ID)
				&& !category.getLevel().equals(CategoryLevel.THIRD.value()))
				&& (!category.getShopId().equals(Constant.PLATFORM_SHOP_ID)
						&& category.getLevel().equals(CategoryLevel.First.value()));
		// 平台分类
		if (isSearch) {
			Result<List<Long>> categoryResponse = categoryFeignClient.listCategoryId(category.getCategoryId());
			categoryIds.addAll(categoryResponse.getData());
		}
		else {
			categoryIds.add(category.getCategoryId());
		}

		if (Objects.equals(category.getShopId(), Constant.PLATFORM_SHOP_ID)) {
			spuIdResponse = productFeignClient.getSpuIdsByCategoryIds(categoryIds);
		}
		else {
			spuIdResponse = productFeignClient.getSpuIdsByShopCategoryIds(categoryIds);
		}
		spuIds.addAll(spuIdResponse.getData());
		return spuIds;
	}

}
