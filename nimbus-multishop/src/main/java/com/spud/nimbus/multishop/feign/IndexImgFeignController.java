package com.spud.nimbus.multishop.feign;

import com.spud.nimbus.api.multishop.feign.IndexImgFeignClient;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.multishop.service.IndexImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author spud
 * @date 2024/2/24
 */
@RestController
public class IndexImgFeignController implements IndexImgFeignClient {

	@Autowired
	private IndexImgService indexImgService;

	@Override
	public Result<Void> deleteBySpuId(Long spuId, Long shopId) {
		indexImgService.deleteBySpuId(spuId, shopId);
		return Result.success(null);
	}

}
