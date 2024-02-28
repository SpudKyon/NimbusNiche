package com.spud.nimbus.api.multishop.feign;

import com.spud.nimbus.common.feign.FeignInsideAuthConfig;
import com.spud.nimbus.common.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author spud
 * @date 2024/2/9
 */
@FeignClient(value = "nimbus-multishop", contextId = "indexImg")
public interface IndexImgFeignClient {

	/**
	 * 根据商品d删除轮播图信息
	 * @param spuId 商品id
	 * @param shopId 店鋪id
	 * @return void
	 */
	@GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/indexImg/deleteBySpuId")
	Result<Void> deleteBySpuId(@RequestParam("spuId") Long spuId, @RequestParam("shopId") Long shopId);

}
