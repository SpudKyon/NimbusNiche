package com.spud.nimbus.product.feign;

import com.spud.nimbus.api.multishop.bo.EsShopDetailBO;
import com.spud.nimbus.api.multishop.feign.ShopDetailFeignClient;
import com.spud.nimbus.api.product.bo.EsProductBO;
import com.spud.nimbus.api.product.feign.ProductFeignClient;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.product.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/10
 */
@RestController
public class ProductFeignController implements ProductFeignClient {

	@Autowired
	private SpuService spuService;

	@Autowired
	private ShopDetailFeignClient shopDetailFeignClient;

	@Override
	public Result<EsProductBO> loadEsProductBO(Long spuId) {
		EsProductBO esProductBO = spuService.loadEsProductBO(spuId);
		// 获取店铺信息
		Result<EsShopDetailBO> shopDetailResponse = shopDetailFeignClient.getShopByShopId(esProductBO.getShopId());
		EsShopDetailBO shopDetail = shopDetailResponse.getData();
		esProductBO.setShopName(shopDetail.getShopName());
		esProductBO.setShopImg(shopDetail.getShopLogo());
		esProductBO.setShopType(shopDetail.getType());
		if (Objects.isNull(esProductBO.getSaleNum())) {
			esProductBO.setSaleNum(0);
		}
		return Result.success(esProductBO);
	}

	@Override
	public Result<List<Long>> getSpuIdsByShopCategoryIds(List<Long> shopCategoryIds) {
		return getSpuIdsBySpuUpdateDTO(shopCategoryIds, null, null, null);
	}

	@Override
	public Result<List<Long>> getSpuIdsByCategoryIds(List<Long> categoryIds) {
		return getSpuIdsBySpuUpdateDTO(null, categoryIds, null, null);
	}

	@Override
	public Result<List<Long>> getSpuIdsByBrandId(Long brandId) {
		return getSpuIdsBySpuUpdateDTO(null, null, brandId, null);
	}

	@Override
	public Result<List<Long>> getSpuIdsByShopId(Long shopId) {
		return getSpuIdsBySpuUpdateDTO(null, null, null, shopId);
	}

	/**
	 * 获取spuId列表
	 * @param shopCategoryIds 店铺分类id列表
	 * @param categoryIds 平台分类Id列表
	 * @param brandId 品牌id
	 * @param shopId 店铺id
	 * @return
	 */
	public Result<List<Long>> getSpuIdsBySpuUpdateDTO(List<Long> shopCategoryIds, List<Long> categoryIds, Long brandId,
			Long shopId) {
		List<Long> spuIds = spuService.getSpuIdsBySpuUpdateDTO(shopCategoryIds, categoryIds, brandId, shopId);
		return Result.success(spuIds);
	}

}