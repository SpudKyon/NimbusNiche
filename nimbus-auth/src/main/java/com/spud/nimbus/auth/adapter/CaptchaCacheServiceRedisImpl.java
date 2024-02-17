package com.spud.nimbus.auth.adapter;

import com.anji.captcha.service.CaptchaCacheService;
import com.spud.nimbus.common.cache.util.RedisUtil;

/**
 * @author spud
 * @date 2024/2/17
 */
public class CaptchaCacheServiceRedisImpl implements CaptchaCacheService {

  @Override
  public void set(String key, String value, long expiresInSeconds) {
    RedisUtil.set(key, value, expiresInSeconds);
  }

  @Override
  public boolean exists(String key) {
    return RedisUtil.hasKey(key);
  }

  @Override
  public void delete(String key) {
    RedisUtil.del(key);
  }

  @Override
  public String get(String key) {
    return RedisUtil.get(key);
  }

  @Override
  public String type() {
    return "redis";
  }

}