package com.spud.nimbus.api.product.feign;

import com.spud.nimbus.api.product.vo.SpuAndSkuVO;
import com.spud.nimbus.api.product.vo.SpuVO;
import com.spud.nimbus.common.feign.FeignInsideAuthConfig;
import com.spud.nimbus.common.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/10
 */
@FeignClient(value = "nimbus-product",contextId = "spu")
public interface SpuFeignClient {

    /**
     * 通过spuId需要搜索的商品
     * @param spuId spuid
     * @return 商品信息(spuId,name,mainImgUrl)
     */
    @GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/spu/getById")
    Result<SpuVO> getById(@RequestParam("spuId") Long spuId);

    /**
     * 通过spuId需要搜索的商品
     * @param spuId spuId
     * @param skuId skuId
     * @return 商品信息
     */
    @GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/spu/getSpuAndSkuById")
    Result<SpuAndSkuVO> getSpuAndSkuById(@RequestParam("spuId") Long spuId, @RequestParam("skuId") Long skuId);


    /**
     * 根据spuId获取spu列表（所需字段：spuId、shopId、name、mainImgUrl）
     * 根据店铺id获取spu列表
     * @param spuIds 商品ids
     * @param spuName 商品名称
     * @param isFailure 是否失效
     * @return 商品列表信息
     */
    @GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/spu/getSpusBySpuIds")
    Result<List<SpuVO>> getSpusBySpuIds(@RequestParam("spuIds") List<Long> spuIds, @RequestParam(value = "spuName",defaultValue = "") String spuName,
                                                      @RequestParam(value = "isFailure",defaultValue = "1") Integer isFailure);

}
