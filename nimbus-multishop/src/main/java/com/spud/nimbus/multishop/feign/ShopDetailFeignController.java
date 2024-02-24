package com.spud.nimbus.multishop.feign;

import com.spud.nimbus.api.multishop.bo.EsShopDetailBO;
import com.spud.nimbus.api.multishop.feign.ShopDetailFeignClient;
import com.spud.nimbus.api.multishop.vo.ShopDetailVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.util.BeanUtil;
import com.spud.nimbus.multishop.model.ShopDetail;
import com.spud.nimbus.multishop.service.ShopDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/24
 */
@RestController
public class ShopDetailFeignController implements ShopDetailFeignClient {

    @Autowired
    private ShopDetailService shopDetailService;


    @Override
    public Result<String> getShopNameByShopId(Long shopId) {
        ShopDetailVO shopDetail = shopDetailService.getByShopId(shopId);
        if (Objects.isNull(shopDetail)) {
            return Result.success("");
        }
        return Result.success(shopDetail.getShopName());
    }

    @Override
    public Result<EsShopDetailBO> getShopByShopId(Long shopId) {
        ShopDetailVO shopDetail = shopDetailService.getByShopId(shopId);
        if (Objects.isNull(shopDetail)) {
            return Result.success(new EsShopDetailBO());
        }
        return Result.success(BeanUtil.map(shopDetail, EsShopDetailBO.class));
    }


    @Override
    public Result<List<ShopDetailVO>> listByShopIds(List<Long> shopIds) {
        List<ShopDetail> shopDetail = shopDetailService.listByShopIds(shopIds);
        return Result.success(BeanUtil.mapAsList(shopDetail, ShopDetailVO.class));
    }

    @Override
    public Result<EsShopDetailBO> shopExtensionData(Long shopId) {
        return Result.success(shopDetailService.shopExtensionData(shopId));
    }

    @Override
    public Result<List<ShopDetailVO>> getShopDetailByShopIdAndShopName(List<Long> shopIds, String shopName) {
        return Result.success(shopDetailService.getShopDetailByShopIdAndShopName(shopIds,shopName));
    }
}
