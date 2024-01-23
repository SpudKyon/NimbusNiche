package com.spud.nimbus.nimbus_payment.dao;

import com.spud.nimbus.nimbus_payment.model.PayInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

}
