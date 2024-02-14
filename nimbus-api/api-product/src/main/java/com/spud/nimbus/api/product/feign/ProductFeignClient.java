package com.spud.nimbus.api.product.feign;

import com.spud.nimbus.api.product.bo.EsProductBO;
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
@FeignClient(value = "nimbus-product",contextId = "product")
public interface ProductFeignClient {

    /**
     * 通过spuId需要搜索的商品
     * @param spuId spuid
     * @return es保存的商品信息
     */
    @GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/product/loadEsProductBO")
    Result<EsProductBO> loadEsProductBO(@RequestParam("spuId") Long spuId);

    /**
     * 根据平台categoryId，获取spuId列表
     * @param shopCategoryIds
     * @return spuId列表
     */
    @GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/product/getSpuIdsByShopCategoryIds")
    Result<List<Long>> getSpuIdsByShopCategoryIds(@RequestParam("shopCategoryIds")List<Long> shopCategoryIds);

    /**
     * 根据categoryId列表，获取spuId列表
     * @param categoryIds
     * @return spuId列表
     */
    @GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/product/getSpuIdsByCategoryIds")
    Result<List<Long>> getSpuIdsByCategoryIds(@RequestParam("categoryIds")List<Long> categoryIds);

    /**
     * 根据brandId，获取spuId列表
     * @param brandId
     * @return spuId列表
     */
    @GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/product/getSpuIdsByBrandId")
    Result<List<Long>> getSpuIdsByBrandId(@RequestParam("brandId")Long brandId);

    /**
     * 根据店铺id，获取spuId列表
     * @param shopId
     * @return spuId列表
     */
    @GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/product/getSpuIdsByShopId")
    Result<List<Long>> getSpuIdsByShopId(@RequestParam("shopId")Long shopId);

}
