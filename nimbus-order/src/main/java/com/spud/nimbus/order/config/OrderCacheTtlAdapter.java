package com.spud.nimbus.order.config;

import com.spud.nimbus.common.cache.adapter.CacheTtlAdapter;
import com.spud.nimbus.common.cache.bo.CacheNameWithTtlBO;
import com.spud.nimbus.common.cache.constant.OrderCacheNames;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author spud
 * @date 2024/2/25
 */
@Component
public class OrderCacheTtlAdapter implements CacheTtlAdapter {
  @Override
  public List<CacheNameWithTtlBO> listCacheNameWithTTL() {
    List<CacheNameWithTtlBO> cacheNameWithTtls = new ArrayList<>();
    // 确认订单缓存30分钟
    cacheNameWithTtls.add(new CacheNameWithTtlBO(OrderCacheNames.ORDER_CONFIRM_UUID_KEY, 60 * 30));
    cacheNameWithTtls.add(new CacheNameWithTtlBO(OrderCacheNames.ORDER_CONFIRM_KEY, 60 * 30));
    return cacheNameWithTtls;
  }
}
