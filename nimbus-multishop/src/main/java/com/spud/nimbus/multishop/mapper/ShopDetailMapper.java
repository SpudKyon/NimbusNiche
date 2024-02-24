package com.spud.nimbus.multishop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spud.nimbus.api.multishop.bo.EsShopDetailBO;
import com.spud.nimbus.api.multishop.vo.ShopDetailVO;
import com.spud.nimbus.multishop.dto.ShopDetailDTO;
import com.spud.nimbus.multishop.model.ShopDetail;
import com.spud.nimbus.multishop.vo.ShopDetailAppVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 店铺详情 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Mapper
public interface ShopDetailMapper extends BaseMapper<ShopDetail> {
  /**
   * 获取店铺详情列表
   *
   * @param shopDetailDTO 店铺搜索数据
   * @return 店铺详情列表
   */
  List<ShopDetailVO> list(@Param("shopDetail") ShopDetailDTO shopDetailDTO);

  /**
   * 根据店铺详情id获取店铺详情
   *
   * @param shopId 店铺详情id
   * @return 店铺详情
   */
  ShopDetailVO getByShopId(@Param("shopId") Long shopId);

  /**
   * 保存店铺详情
   *
   * @param shopDetail 店铺详情
   */
  void save(@Param("shopDetail") ShopDetail shopDetail);

  /**
   * 更新店铺详情
   *
   * @param shopDetail 店铺详情
   */
  void update(@Param("shopDetail") ShopDetail shopDetail);

  /**
   * 根据店铺详情id删除店铺详情
   *
   * @param shopId
   */
  void deleteById(@Param("shopId") Long shopId);

  /**
   * 根据店铺id列表，获取店铺信息
   *
   * @param shopIds 店铺id列表
   * @return 店铺列表
   */
  List<ShopDetail> listByShopIds(@Param("shopIds") List<Long> shopIds);

  /**
   * 店铺搜索列表
   *
   * @param shopDetailDTO
   * @return
   */
  List<ShopDetailAppVO> shopSearchList(@Param("shopDetail") ShopDetailDTO shopDetailDTO);

  /**
   * 统计该店铺名被其他用户使用的数量
   *
   * @param shopName
   * @param shopId
   * @return
   */
  int countShopName(@Param("shopName") String shopName, @Param("shopId") Long shopId);

  /**
   * 改变店铺状态
   *
   * @param shopId
   * @param shopStatus
   */
  void changeSpuStatus(@Param("shopId") Long shopId, @Param("shopStatus") Integer shopStatus);

  /**
   * 获取店铺信息及扩展信息
   *
   * @param shopId
   * @return
   */
  EsShopDetailBO shopExtensionData(@Param("shopId") Long shopId);

  /**
   * 获取店铺信息及扩展信息
   *
   * @param shopIds  店铺ids
   * @param shopName 店铺名称
   * @return 店铺信息列表
   */
  List<ShopDetailVO> getShopDetailByShopIdAndShopName(@Param("shopIds") List<Long> shopIds, @Param("shopName") String shopName);

  /**
   * 根据店铺id获取店铺扩展信息
   *
   * @param shopId 店铺id
   * @return 店铺扩展信息
   */
  ShopDetailVO getShoExtensionsByShopId(Long shopId);
}