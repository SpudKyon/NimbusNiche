package com.spud.nimbus.common.security.adapter;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/9
 */
public interface AuthConfigAdapter {

  /**
   * 需要授权登陆的路径
   * @return 需要授权登陆的路径列表
   */
  List<String> pathPatterns();

  /**
   * 不需要授权登陆的路径
   * @param paths
   * @return 不需要授权登陆的路径列表
   */
  List<String> excludePathPatterns(String... paths);

}
