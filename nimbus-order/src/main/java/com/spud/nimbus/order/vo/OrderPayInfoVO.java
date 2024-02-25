package com.spud.nimbus.order.vo;

import com.spud.nimbus.common.vo.BaseVO;
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
public class OrderPayInfoVO extends BaseVO {
  private static final long serialVersionUID = 1L;

  @Schema(description = "支付单号")
  private Long payId;

  @Schema(description = "用户id")
  private Long userId;

  @Schema(description = "外部订单流水号")
  private String bizPayNo;

  @Schema(description = "系统类型 见SysTypeEnum")
  private Integer sysType;

  @Schema(description = "支付状态")
  private Integer payStatus;

  @Schema(description = "支付金额")
  private Long payAmount;

  @Schema(description = "版本号")
  private Integer version;

  @Schema(description = "回调内容")
  private String callbackContent;

  @Schema(description = "回调时间")
  private Date callbackTime;

  @Schema(description = "确认时间")
  private Date confirmTime;
}
