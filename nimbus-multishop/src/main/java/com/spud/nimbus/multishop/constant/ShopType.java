package com.spud.nimbus.multishop.constant;

/**
 * @author spud
 * @date 2024/2/23
 */
public enum ShopType {

  /**
   * 自营店
   */
  SELF_SHOP(-1),
  /**
   * 其他店铺
   */
  STOP(0);

  private Integer num;

  ShopType(Integer num) {
    this.num = num;
  }

  public static ShopType instance(Integer value) {
    ShopType[] enums = values();
    for (ShopType statusEnum : enums) {
      if (statusEnum.value().equals(value)) {
        return statusEnum;
      }
    }
    return null;
  }

  public Integer value() {
    return num;
  }
}
