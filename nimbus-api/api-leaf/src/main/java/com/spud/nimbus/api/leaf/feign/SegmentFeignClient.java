package com.spud.nimbus.api.leaf.feign;

import com.spud.nimbus.common.feign.FeignInsideAuthConfig;
import com.spud.nimbus.common.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author spud
 * @date 2024/1/30
 */
@FeignClient(value = "nimbus-leaf", contextId = "segment")
public interface SegmentFeignClient {

	/**
	 * 获取id
	 */
	@GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/segment")
	Result<Long> getSegmentId(@RequestParam("key") String key);

}
