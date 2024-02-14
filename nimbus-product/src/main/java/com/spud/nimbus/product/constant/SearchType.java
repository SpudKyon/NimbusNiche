package com.spud.nimbus.product.constant;

/**
 * @author spud
 * @date 2024/2/10
 */
public enum SearchType {

  /**
   * 不需要作为搜索参数
   */
  NOT_SEARCH(0),

  /**
   * 搜索参数
   */
  SEARCH(1)
  ;

  private final Integer value;

  public Integer value() {
    return value;
  }

  SearchType(Integer value) {
    this.value = value;
  }

}