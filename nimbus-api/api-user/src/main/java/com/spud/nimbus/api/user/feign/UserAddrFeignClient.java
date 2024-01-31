package com.spud.nimbus.api.user.feign;

import com.spud.nimbus.common.feign.FeignInsideAuthConfig;
import com.spud.nimbus.common.order.vo.UserAddrVO;
import com.spud.nimbus.common.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author spud
 * @date 2024/1/30
 */
@FeignClient(value = "nimbus-user",contextId = "userAddr")
public interface UserAddrFeignClient {

  /**
   * 根据地址id获取用户地址信息
   * @param addrId 地址id
   * @return 用户地址信息
   */
  @GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/userAddr/getUserAddrByAddrId")
  Result<UserAddrVO> getUserAddrByAddrId(@RequestParam("addrId") Long addrId);

}
