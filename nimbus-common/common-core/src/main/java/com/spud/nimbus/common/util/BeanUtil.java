package com.spud.nimbus.common.util;


import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.util.List;

/**
 * @author spud
 * @date 2024/1/25
 */
public class BeanUtil {
  public static <S, D> List<D> mapAsList(final Iterable<S> sourceObject, Class<D> clazz) {
    return JSONArray.parseArray(JSONArray.toJSONString(sourceObject), clazz);
  }

  public static <S, D> D map(final S sourceObject, Class<D> clazz) {
    return JSONObject.parseObject(JSONObject.toJSONString(sourceObject), clazz);
  }
}
