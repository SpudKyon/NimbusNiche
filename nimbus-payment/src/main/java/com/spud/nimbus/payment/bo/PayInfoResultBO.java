package com.spud.nimbus.payment.bo;

import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/26
 */
@Data
@ToString
public class PayInfoResultBO {

  /**
   * 商城支付单号
   */
  private Long payId;

  /**
   * 第三方订单流水号
   */
  private String bizPayNo;

  /**
   * 是否支付成功
   */
  private Integer isPaySuccess;

  /**
   * 支付成功的标记
   */
  private String successString;

  /**
   * 支付金额
   */
  private Long payAmount;

  /**
   * 回调内容
   */
  private String callbackContent;
}
