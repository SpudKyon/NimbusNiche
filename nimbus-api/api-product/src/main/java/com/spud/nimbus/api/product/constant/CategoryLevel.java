package com.spud.nimbus.api.product.constant;

/**
 * @author spud
 * @date 2024/2/9
 */
public enum CategoryLevel {

  /**
   * 第一级
   */
  First(0),

  /**
   * 第二级
   */
  SECOND(1),

  /**
   * 第三级
   */
  THIRD(2)
  ;

  private final Integer value;

  public Integer value() {
    return value;
  }

  CategoryLevel(Integer value) {
    this.value = value;
  }

}