package com.spud.nimbus.nimbus_nacos.service.impl;

import com.spud.nimbus.nimbus_nacos.model.TenantInfo;
import com.spud.nimbus.nimbus_nacos.dao.TenantInfoMapper;
import com.spud.nimbus.nimbus_nacos.service.TenantInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * tenant_info 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class TenantInfoServiceImpl extends ServiceImpl<TenantInfoMapper, TenantInfo> implements TenantInfoService {

}
