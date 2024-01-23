package com.spud.nimbus.nimbus_payment.service.impl;

import com.spud.nimbus.nimbus_payment.model.PayInfo;
import com.spud.nimbus.nimbus_payment.dao.PayInfoMapper;
import com.spud.nimbus.nimbus_payment.service.PayInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单支付记录 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class PayInfoServiceImpl extends ServiceImpl<PayInfoMapper, PayInfo> implements PayInfoService {

}
