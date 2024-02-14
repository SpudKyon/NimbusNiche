package com.spud.nimbus.api.platform.feign;

import com.spud.nimbus.common.feign.FeignInsideAuthConfig;
import com.spud.nimbus.common.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author spud
 * @date 2024/2/9
 */
@FeignClient(value = "nimbus-platform",contextId = "config")
public interface ConfigFeignClient {

  /**
   * 获取配置信息
   * @param key key
   * @return 配置信息json
   */
  @GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/config/getConfig")
  Result<String> getConfig(@RequestParam("key") String key);
}