package com.spud.nimbus.product.controller.app;

import cn.hutool.core.collection.CollectionUtil;
import com.spud.nimbus.api.product.manage.ShopCartAdapter;
import com.spud.nimbus.api.product.vo.SkuVO;
import com.spud.nimbus.api.product.vo.SpuVO;
import com.spud.nimbus.common.constant.StatusEnum;
import com.spud.nimbus.common.order.vo.ShopCartItemVO;
import com.spud.nimbus.common.order.vo.ShopCartVO;
import com.spud.nimbus.common.order.vo.ShopCartWithAmountVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.response.ResultCode;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.common.util.BeanUtil;
import com.spud.nimbus.product.dto.shopcart.ChangeShopCartItemDTO;
import com.spud.nimbus.product.dto.shopcart.CheckShopCartItemDTO;
import com.spud.nimbus.product.model.ShopCartItem;
import com.spud.nimbus.product.service.ShopCartItemService;
import com.spud.nimbus.product.service.SkuService;
import com.spud.nimbus.product.service.SpuService;
import com.spud.nimbus.product.vo.ShopCartAmountVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/13
 */
@RestController
@RequestMapping("v1/a/shop_cart")
@Tag(name = "app-购物车")
public class ShopCartController {

	private final ShopCartItemService shopCartService;

	private final SpuService spuService;

	private final SkuService skuService;

	private final ShopCartAdapter shopCartAdapter;

	@Autowired
	public ShopCartController(ShopCartItemService shopCartService, SpuService spuService, SkuService skuService, ShopCartAdapter shopCartAdapter) {
		this.shopCartService = shopCartService;
		this.spuService = spuService;
		this.skuService = skuService;
		this.shopCartAdapter = shopCartAdapter;
	}

	/**
	 * 获取用户购物车信息
	 * @return
	 */
	@GetMapping("/info")
	@Operation(summary = "获取用户购物车信息", description = "获取用户购物车信息")
	public Result<ShopCartWithAmountVO> info() {
		// 拿到购物车的所有item
		List<ShopCartItemVO> shopCartItems = shopCartService.getShopCartItems();
		List<ShopCartVO> shopCarts = shopCartAdapter.conversionShopCart(shopCartItems);
		ShopCartWithAmountVO shopCartWithAmountVO = new ShopCartWithAmountVO();
		long total = 0L;
		for (ShopCartItemVO shopCartItem : shopCartItems) {
			if (Objects.equals(shopCartItem.getIsChecked(), 1)) {
				total += shopCartItem.getTotalAmount();
			}
		}
		shopCartWithAmountVO.setShopCarts(shopCarts);
		shopCartWithAmountVO.setTotalMoney(total);
		return Result.success(shopCartWithAmountVO);
	}

	/**
	 * 获取用户购物车信息
	 * @return
	 */
	@GetMapping("/amount_info")
	@Operation(summary = "获取用户购物车金额信息", description = "获取用户购物车金额信息")
	public Result<ShopCartAmountVO> amountInfo() {
		// 拿到购物车的所有item
		List<ShopCartItemVO> shopCartItems = shopCartService.getShopCartItems();
		List<ShopCartVO> shopCarts = shopCartAdapter.conversionShopCart(shopCartItems);
		ShopCartWithAmountVO shopCartWithAmountVO = new ShopCartWithAmountVO();
		shopCartWithAmountVO.setShopCarts(shopCarts);

		return Result.success(BeanUtil.map(shopCartWithAmountVO, ShopCartAmountVO.class));
	}

	@DeleteMapping("/delete_item")
	@Operation(summary = "删除用户购物车物品", description = "通过购物车id删除用户购物车物品")
	public Result<Void> deleteItem(@RequestBody List<Long> shopCartItemIds) {
		Long userId = AuthUserContext.get().getUserId();
		shopCartService.deleteShopCartItemsByShopCartItemIds(userId, shopCartItemIds);
		return Result.success(null);
	}

	@DeleteMapping("/delete_all")
	@Operation(summary = "清空用户购物车所有物品", description = "清空用户购物车所有物品")
	public Result<String> deleteAll() {
		Long userId = AuthUserContext.get().getUserId();
		shopCartService.deleteAllShopCartItems(userId);
		// 删除成功
		return Result.success(null);
	}

	@PostMapping("/check_items")
	@Operation(summary = "", description = "")
	public Result<Void> checkItems(@Valid @RequestBody List<CheckShopCartItemDTO> params) {
		if (CollectionUtil.isEmpty(params)) {
			return Result.success(null);
		}
		Long userId = AuthUserContext.get().getUserId();
		shopCartService.checkShopCartItems(userId, params);
		return Result.success(null);
	}

	@PostMapping("/change_item")
	@Operation(summary = "添加、修改用户购物车物品",
			description = "通过商品id(prodId)、skuId、店铺Id(shopId),添加/修改用户购物车商品，并传入改变的商品个数(count)，"
					+ "当count为正值时，增加商品数量，当count为负值时，将减去商品的数量，当最终count值小于0时，会将商品从购物车里面删除")
	public Result<Void> addItem(@Valid @RequestBody ChangeShopCartItemDTO param) {

		// 不用校验库存是否充足！！！
		Long userId = AuthUserContext.get().getUserId();
		List<ShopCartItemVO> shopCartItems = shopCartService.getShopCartItems();

		SpuVO spu = spuService.getBySpuId(param.getSpuId());
		SkuVO sku = skuService.getSkuBySkuId(param.getSkuId());

		// 当商品状态不正常时，不能添加到购物车
		if (Objects.isNull(spu) || Objects.isNull(sku) || !Objects.equals(spu.getStatus(), StatusEnum.ENABLE.value())
				|| !Objects.equals(sku.getStatus(), StatusEnum.ENABLE.value())
				|| !Objects.equals(sku.getSpuId(), spu.getSpuId())) {
			// 当返回商品不存在时，前端应该将商品从购物车界面移除
			return Result.fail(ResultCode.SPU_NOT_EXIST, null);
		}
		// 保存shopId，不要让前端传过来
		param.setShopId(spu.getShopId());

		Integer oldCount = 0;
		Long oldShopCartItemId = null;
		for (ShopCartItemVO shopCartItemVo : shopCartItems) {
			if (Objects.equals(param.getSkuId(), shopCartItemVo.getSkuId())) {
				// 旧数量
				oldCount = shopCartItemVo.getCount();
				oldShopCartItemId = shopCartItemVo.getCartItemId();
				ShopCartItem shopCartItem = new ShopCartItem();
				shopCartItem.setUserId(userId);
				shopCartItem.setCount(param.getCount() + shopCartItemVo.getCount());
				shopCartItem.setIsChecked(shopCartItemVo.getIsChecked());
				shopCartItem.setCartItemId(shopCartItemVo.getCartItemId());
				// 如果有个旧的sku，就说明是在切换sku
				if (Objects.nonNull(param.getOldSkuId())) {
					continue;
				}
				// 防止购物车变成负数，从购物车删除
				if (shopCartItem.getCount() <= 0) {
					shopCartService.deleteShopCartItemsByShopCartItemIds(userId,
							Collections.singletonList(shopCartItem.getCartItemId()));
					return Result.success(null);
				}
				shopCartService.updateShopCartItem(userId, shopCartItem);
				return Result.success(null);
			}
		}

		if (Objects.nonNull(param.getOldSkuId())) {
			for (ShopCartItemVO oldShopCartItem : shopCartItems) {
				// 旧sku
				if (Objects.equals(param.getOldSkuId(), oldShopCartItem.getSkuId())) {
					ShopCartItem shopCartItem = new ShopCartItem();
					shopCartItem.setUserId(userId);
					shopCartItem.setCartItemId(oldShopCartItem.getCartItemId());
					// 如果以前就存在这个商品，还要把以前的商品数量累加
					shopCartItem.setCount(param.getCount() + oldCount);
					shopCartItem.setSkuId(param.getSkuId());

					if (oldShopCartItemId != null) {
						// 删除旧的购物项
						shopCartService.deleteShopCartItemsByShopCartItemIds(userId,
								Collections.singletonList(oldShopCartItemId));
					}
					// 更新购物车
					shopCartService.updateShopCartItem(userId, shopCartItem);
					return Result.success(null);
				}
			}
		}

		// 所有都正常时
		shopCartService.addShopCartItem(userId, param, sku.getPriceFee());
		// 添加成功
		return Result.success(null);
	}

	@GetMapping("/prod_count")
	@Operation(summary = "获取购物车商品数量", description = "获取购物车商品数量")
	public Result<Integer> prodCount() {
		return Result.success(shopCartService.getShopCartItemCount(AuthUserContext.get().getUserId()));
	}

	@GetMapping("/expiry_prod_list")
	@Operation(summary = "获取购物车失效商品信息", description = "获取购物车失效商品列表")
	public Result<List<ShopCartItemVO>> expiryProdList() {
		return Result.success(shopCartService.getShopCartExpiryItems());
	}

}
