package com.spud.nimbus.common.security.adapter;

import com.spud.nimbus.common.feign.FeignInsideAuthConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author spud
 * @date 2024/2/9
 */
@Slf4j
public class DefaultAuthConfigAdapter implements AuthConfigAdapter {

  public DefaultAuthConfigAdapter() {
    log.info("not implement other AuthConfigAdapter, use DefaultAuthConfigAdapter... all url need auth...");
  }

  /**
   * 内部直接调用接口，无需登录权限
   */
  private static final String FEIGN_INSIDER_URI = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/**";

  /**
   * 外部直接调用接口，无需登录权限 unwanted auth
   */
  private static final String EXTERNAL_URI = "/**/ua/**";

  /**
   * swagger
   */
  private static final String DOC_URI = "/v3/api-docs/default";

  @Override
  public List<String> pathPatterns() {
    return Collections.singletonList("/*");
  }

  @Override
  public List<String> excludePathPatterns(String... paths) {
    List<String> arrayList = new ArrayList<>();
    arrayList.add(DOC_URI);
    arrayList.add(FEIGN_INSIDER_URI);
    arrayList.add(EXTERNAL_URI);
    arrayList.addAll(Arrays.asList(paths));
    return arrayList;
  }
}