package com.spud.nimbus.platform.feign;

import com.spud.nimbus.api.platform.feign.ConfigFeignClient;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.platform.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author spud
 * @date 2024/2/25
 */
@RestController
public class ConfigFeignController implements ConfigFeignClient {

    @Autowired
    private SysConfigService sysConfigService;

    @Override
    public Result<String> getConfig(String key) {
        return Result.success(sysConfigService.getValue(key));
    }
}
