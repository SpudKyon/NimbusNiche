package com.spud.nimbus.order.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author spud
 * @date 2024/2/25
 */
@Data
@ToString
public class SubmitOrderPayAmountInfoBO {

  @Schema(description = "创建时间")
  private Date createTime;

  @Schema(description = "总共需要支付金额")
  private Long totalFee;

  @Schema(description = "订单地址id")
  private Long orderAddrId;

}
