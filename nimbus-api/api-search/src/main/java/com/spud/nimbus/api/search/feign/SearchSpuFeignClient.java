package com.spud.nimbus.api.search.feign;

import com.spud.nimbus.api.search.dto.EsPageDTO;
import com.spud.nimbus.api.search.dto.ProductSearchDTO;
import com.spud.nimbus.api.search.vo.EsPageVO;
import com.spud.nimbus.api.search.vo.search.ProductSearchVO;
import com.spud.nimbus.api.search.vo.search.SpuSearchVO;
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
@FeignClient(value = "nimbus-search",contextId = "searchSpu")
public interface SearchSpuFeignClient {

  /**
   * 商品搜索
   * @param pageDTO
   * @param productSearchDTO
   * @return
   */
  @GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/searchSpu/search")
  Result<EsPageVO<ProductSearchVO>> search(@RequestParam("pageDTO") EsPageDTO pageDTO, @RequestParam("productSearchDTO") ProductSearchDTO productSearchDTO);
  /**
   * 根据spuId列表， 获取spu列表信息
   * @param spuIds 商品id列表
   * @return 商品列表
   */
  @GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/searchSpu/getSpusBySpuIds")
  Result<List<SpuSearchVO>> getSpusBySpuIds(@RequestParam("spuIds") List<Long> spuIds);

  /**
   * 根据店铺，获取商品分页信息
   * @param pageNum 分页数
   * @param pageSize 每页大小
   * @param shopId 店铺id
   * @return
   */
  @GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/searchSpu/spuPage")
  Result<EsPageVO<ProductSearchVO>> spuPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestParam("shopId") Long shopId);

  /**
   * 根据店铺id列表获取每个店铺的spu列表
   * @param shopIds 店铺id列表
   * @param size 每个店铺返回的商品数量
   * @return
   */
  @GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/searchSpu/limitSizeListByShopIds")
  Result<List<SpuSearchVO>> limitSizeListByShopIds(@RequestParam("shopIds") List<Long> shopIds, @RequestParam("size") Integer size);

}
