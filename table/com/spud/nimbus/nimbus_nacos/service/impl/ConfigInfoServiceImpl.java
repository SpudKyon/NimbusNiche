package com.spud.nimbus.nimbus_nacos.service.impl;

import com.spud.nimbus.nimbus_nacos.model.ConfigInfo;
import com.spud.nimbus.nimbus_nacos.dao.ConfigInfoMapper;
import com.spud.nimbus.nimbus_nacos.service.ConfigInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * config_info 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class ConfigInfoServiceImpl extends ServiceImpl<ConfigInfoMapper, ConfigInfo> implements ConfigInfoService {

}
