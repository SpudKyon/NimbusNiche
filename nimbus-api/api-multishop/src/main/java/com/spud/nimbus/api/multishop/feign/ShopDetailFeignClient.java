package com.spud.nimbus.api.multishop.feign;

import com.spud.nimbus.api.multishop.bo.EsShopDetailBO;
import com.spud.nimbus.api.multishop.vo.ShopDetailVO;
import com.spud.nimbus.common.feign.FeignInsideAuthConfig;
import com.spud.nimbus.common.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/9
 */
@FeignClient(value = "nimbus-multishop", contextId = "shopDetail")
public interface ShopDetailFeignClient {

	/**
	 * 根据店铺id获取店铺名称
	 * @param shopId 店铺id
	 * @return 店铺名称
	 */
	@GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/shopDetail/getShopNameByShopId")
	Result<String> getShopNameByShopId(@RequestParam("shopId") Long shopId);

	/**
	 * 根据店铺id获取店铺信息
	 * @param shopId 店铺id
	 * @return 店铺信息
	 */
	@GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/shopDetail/getShopByShopId")
	Result<EsShopDetailBO> getShopByShopId(@RequestParam("shopId") Long shopId);

	/**
	 * 根据店铺id列表， 获取店铺列表信息
	 * @param shopIds 店铺id列表
	 * @return 店铺列表信息
	 */
	@GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/shopDetail/listByShopIds")
	Result<List<ShopDetailVO>> listByShopIds(@RequestParam("shopIds") List<Long> shopIds);

	/**
	 * 获取店铺信息及扩展信息
	 * @param shopId 店铺id
	 * @return 店铺信息及扩展信息
	 */
	@GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/shopDetail/getShopExtension")
	Result<EsShopDetailBO> shopExtensionData(@RequestParam("shopId") Long shopId);

	/**
	 * 获取店铺信息及扩展信息
	 * @param shopIds 店铺ids
	 * @param shopName 店铺名称
	 * @return 店铺信息列表
	 */
	@GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX
			+ "/insider/shopDetail/getShopDetailByShopIdAndShopName")
	Result<List<ShopDetailVO>> getShopDetailByShopIdAndShopName(@RequestParam("shopIds") List<Long> shopIds,
			@RequestParam(value = "shopName", defaultValue = "") String shopName);

}