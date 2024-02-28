package com.spud.nimbus.api.product.feign;

import com.spud.nimbus.api.product.vo.SkuVO;
import com.spud.nimbus.common.feign.FeignInsideAuthConfig;
import com.spud.nimbus.common.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author spud
 * @date 2024/2/10
 */
@FeignClient(value = "nimbus-product", contextId = "sku")
public interface SkuFeignClient {

	/**
	 * 通过skuId获取sku信息
	 * @param skuId skuId
	 * @return sku信息
	 */
	@GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/sku/getById")
	Result<SkuVO> getById(@RequestParam("skuId") Long skuId);

}
