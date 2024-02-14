package com.spud.nimbus.product.feign;

import cn.hutool.core.collection.CollectionUtil;
import com.spud.nimbus.api.product.feign.ShopCartFeignClient;
import com.spud.nimbus.common.order.vo.ShopCartItemVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.product.service.ShopCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/10
 */
@RestController
public class ShopCartFeignController implements ShopCartFeignClient {

  @Autowired
  private ShopCartItemService shopCartService;

  @Override
  public Result<List<ShopCartItemVO>> getCheckedShopCartItems() {
    List<ShopCartItemVO> checkedShopCartItems = shopCartService.getCheckedShopCartItems();
    if (CollectionUtil.isNotEmpty(checkedShopCartItems)) {
      for (ShopCartItemVO shopCartItem : checkedShopCartItems) {
        shopCartItem.setTotalAmount(shopCartItem.getCount() * shopCartItem.getSkuPriceFee());
      }
    }
    return Result.success(checkedShopCartItems);
  }

  @Override
  public Result<Void> deleteItem(List<Long> shopCartItemIds) {
    Long userId = AuthUserContext.get().getUserId();
    shopCartService.deleteShopCartItemsByShopCartItemIds(userId,shopCartItemIds);
    return Result.success(null);
  }
}