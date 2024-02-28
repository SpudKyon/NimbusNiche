package com.spud.nimbus.api.product.feign;

import com.spud.nimbus.common.feign.FeignInsideAuthConfig;
import com.spud.nimbus.common.order.vo.ShopCartItemVO;
import com.spud.nimbus.common.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/10
 */
@FeignClient(value = "nimbus-product", contextId = "shopCart")
public interface ShopCartFeignClient {

	/**
	 * 获取购物项
	 * @return 购物项
	 */
	@GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/shopCart/getById")
	Result<List<ShopCartItemVO>> getCheckedShopCartItems();

	/**
	 * 通过购物车id删除用户购物车物品
	 * @param shopCartItemIds 购物车id
	 * @return
	 */
	@DeleteMapping("/delete_item")
	Result<Void> deleteItem(@RequestBody List<Long> shopCartItemIds);

}
