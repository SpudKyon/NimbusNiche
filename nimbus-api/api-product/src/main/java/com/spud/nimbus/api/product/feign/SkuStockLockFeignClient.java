package com.spud.nimbus.api.product.feign;

import com.spud.nimbus.api.product.dto.SkuStockLockDTO;
import com.spud.nimbus.common.feign.FeignInsideAuthConfig;
import com.spud.nimbus.common.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/10
 */
@FeignClient(value = "nimbus-product",contextId = "skuStockLock")
public interface SkuStockLockFeignClient {

    /**
     * 锁定库存
     * @param skuStockLocks 参数
     * @return 是否成功
     */
    @PostMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/skuStockLock/lock")
    Result<Void> lock(@RequestBody List<SkuStockLockDTO> skuStockLocks);

}
