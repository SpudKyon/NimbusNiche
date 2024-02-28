package com.spud.nimbus.product.feign;

import com.spud.nimbus.api.product.dto.SkuStockLockDTO;
import com.spud.nimbus.api.product.feign.SkuStockLockFeignClient;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.product.service.SkuStockLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/10
 */
@RestController
public class SkuStockLockFeignController implements SkuStockLockFeignClient {

	@Autowired
	private SkuStockLockService skuStockLockService;

	@Override
	public Result<Void> lock(List<SkuStockLockDTO> skuStockLocksParam) {
		return skuStockLockService.lock(skuStockLocksParam);
	}

}