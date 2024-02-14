package com.spud.nimbus.product.feign;

import com.spud.nimbus.api.product.feign.SkuFeignClient;
import com.spud.nimbus.api.product.vo.SkuVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.product.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author spud
 * @date 2024/2/10
 */
@RestController
public class SkuFeignController implements SkuFeignClient {

  @Autowired
  private SkuService skuService;


  @Override
  public Result<SkuVO> getById(Long skuId) {
    return Result.success(skuService.getSkuBySkuId(skuId));
  }
}
