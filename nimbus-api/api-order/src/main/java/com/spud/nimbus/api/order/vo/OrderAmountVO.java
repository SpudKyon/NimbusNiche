package com.spud.nimbus.api.order.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/9
 */
@Data
@ToString
public class OrderAmountVO {

  /**
   * 支付金额
   */
  private Long payAmount;
}
