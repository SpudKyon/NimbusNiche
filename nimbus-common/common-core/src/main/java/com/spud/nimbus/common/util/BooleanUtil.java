package com.spud.nimbus.common.util;

import java.util.Objects;

/**
 * @author spud
 * @date 2024/1/25
 */
public class BooleanUtil {

  /**
   * 判断一个数字是否为true（等于1就是true）
   *
   * @param num 输入的数字
   * @return 是否为true
   */
  public static boolean isTrue(Integer num) {
    if (num == null) {
      return false;
    }
    return Objects.equals(num, 1);
  }

  public static boolean isFalse(Integer num) {
    return !isTrue(num);
  }

}
