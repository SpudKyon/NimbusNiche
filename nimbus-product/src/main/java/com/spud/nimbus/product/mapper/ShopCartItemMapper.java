package com.spud.nimbus.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spud.nimbus.common.order.vo.ShopCartItemVO;
import com.spud.nimbus.product.dto.shopcart.CheckShopCartItemDTO;
import com.spud.nimbus.product.model.ShopCartItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 购物车 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Mapper
public interface ShopCartItemMapper extends BaseMapper<ShopCartItem> {

	/**
	 * 获取购物车列表
	 * @return 购物车列表
	 */
	List<ShopCartItemVO> list();

	/**
	 * 根据购物车id获取购物车
	 * @param cartItemId 购物车id
	 * @return 购物车
	 */
	ShopCartItemVO getByCartId(@Param("cartItemId") Long cartItemId);

	/**
	 * 保存购物车
	 * @param shopCartItem 购物车
	 */
	void save(@Param("shopCartItem") ShopCartItem shopCartItem);

	/**
	 * 更新购物车
	 * @param shopCartItem 购物车
	 */
	void update(@Param("shopCartItem") ShopCartItem shopCartItem);

	/**
	 * 根据购物车id删除购物车
	 * @param cartItemId
	 */
	void deleteById(@Param("cartItemId") Long cartItemId);

	/**
	 * 根据用户id获取用户的购物车信息
	 * @param userId 用户id
	 * @param isExpiry 是否已过期
	 * @param isChecked 是否被选中
	 * @return 购物车项
	 */
	List<ShopCartItemVO> getShopCartItems(@Param("userId") Long userId, @Param("isExpiry") Boolean isExpiry,
			@Param("isChecked") Boolean isChecked);

	/**
	 * 根据购物车项id删除购物车
	 * @param userId 用户id
	 * @param shopCartItemIds 购物车项id
	 */
	void deleteShopCartItemsByShopCartItemIds(@Param("userId") Long userId,
			@Param("shopCartItemIds") List<Long> shopCartItemIds);

	/**
	 * 清空用户购物车
	 * @param userId 用户id
	 */
	void deleteAllShopCartItems(@Param("userId") Long userId);

	/**
	 * 根据商品id，获取用户id，用于清空购物车商品数量的缓存
	 * @param spuId 商品id
	 * @return 用户id列表
	 */
	List<String> listUserIdBySpuId(@Param("spuId") Long spuId);

	/**
	 * 购物项数量，有缓存
	 * @param userId 用户id
	 * @return 购物项数量
	 */
	Integer getShopCartItemCount(@Param("userId") Long userId);

	/**
	 * 勾选购车车状态
	 * @param userId 用户id
	 * @param checkShopCartItems 参数
	 */
	void checkShopCartItems(@Param("userId") Long userId,
			@Param("checkShopCartItems") List<CheckShopCartItemDTO> checkShopCartItems);

}
