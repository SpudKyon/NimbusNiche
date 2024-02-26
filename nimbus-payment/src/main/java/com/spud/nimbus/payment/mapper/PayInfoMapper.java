package com.spud.nimbus.payment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spud.nimbus.payment.model.PayInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 订单支付记录 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Mapper
public interface PayInfoMapper extends BaseMapper<PayInfo> {

  /**
   * 根据订单支付记录id获取订单支付记录
   *
   * @param payId 订单支付记录id
   * @return 订单支付记录
   */
  PayInfo getByPayId(@Param("payId") Long payId);

  /**
   * 保存订单支付记录
   *
   * @param payInfo 订单支付记录
   */
  void save(@Param("payInfo") PayInfo payInfo);

  /**
   * 更新订单支付记录
   *
   * @param payInfo 订单支付记录
   */
  void update(@Param("payInfo") PayInfo payInfo);

  /**
   * 根据订单支付记录id删除订单支付记录
   *
   * @param payId
   */
  void deleteById(@Param("payId") Long payId);

  /**
   * 根据支付订单号获取订单支付状态
   *
   * @param orderIds 订单号ids
   * @return 支付状态
   */
  Integer getPayStatusByOrderIds(@Param("orderIds") String orderIds);

  /**
   * 查询订单是否已经支付
   *
   * @param orderIds 订单id
   * @param userId   用户id
   * @return 是否已经支付
   */
  Integer isPay(@Param("orderIds") String orderIds, @Param("userId") Long userId);
}