package com.spud.nimbus.multishop.controller.app;

import com.spud.nimbus.api.multishop.vo.ShopDetailVO;
import com.spud.nimbus.common.exception.NimbusException;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.multishop.service.ShopDetailService;
import com.spud.nimbus.multishop.vo.ShopHeadInfoVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/24
 */
@RequestMapping(value = "/ua/shop_detail")
@RestController("appShopDetailController")
@Tag(name = "app-店铺详情信息")
public class ShopDetailController {

  @Autowired
  private ShopDetailService shopDetailService;

  @GetMapping("/check_shop_name")
  @Operation(summary = "验证店铺名称是否重名", description = "验证店铺名称是否重名")
  public Result<Boolean> checkShopName(@RequestParam("shopName") String shopName) {
    Boolean res = shopDetailService.checkShopName(shopName);
    return Result.success(res);
  }

  @GetMapping("/head_info")
  @Operation(summary = "店铺头部信息", description = "店铺头部信息")
  public Result<ShopHeadInfoVO> getShopHeadInfo(Long shopId) {
    ShopHeadInfoVO shopHeadInfoVO = new ShopHeadInfoVO();
    ShopDetailVO shopDetailVO = shopDetailService.getByShopId(shopId);
    if (Objects.isNull(shopDetailVO)) {
      throw new NimbusException("店铺不存在");
    }
    shopHeadInfoVO.setShopStatus(shopDetailVO.getShopStatus());
    if (!Objects.equals(shopDetailVO.getShopStatus(), 1)) {
      return Result.success(shopHeadInfoVO);
    }
    shopHeadInfoVO.setShopId(shopId);
    shopHeadInfoVO.setType(shopDetailVO.getType());
    shopHeadInfoVO.setIntro(shopDetailVO.getIntro());
    shopHeadInfoVO.setShopLogo(shopDetailVO.getShopLogo());
    shopHeadInfoVO.setShopName(shopDetailVO.getShopName());
    shopHeadInfoVO.setMobileBackgroundPic(shopDetailVO.getMobileBackgroundPic());
    return Result.success(shopHeadInfoVO);
  }
}
