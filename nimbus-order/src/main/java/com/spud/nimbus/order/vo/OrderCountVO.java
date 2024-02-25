package com.spud.nimbus.order.vo;

import com.spud.nimbus.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/25
 */
@Data
@ToString
@Schema(description = "我的订单数量")
public class OrderCountVO extends BaseVO {

  @Schema(description = "所有订单数量")
  private Integer allCount;

  @Schema(description = "待付款")
  private Integer unPay;

  @Schema(description = "待发货")
  private Integer payed;

  @Schema(description = "待收货")
  private Integer consignment;

  @Schema(description = "已完成")
  private Integer success;

  public Integer getAllCount() {
    return allCount;
  }

  public void setAllCount(Integer allCount) {
    this.allCount = allCount;
  }

  public Integer getUnPay() {
    return unPay;
  }

  public void setUnPay(Integer unPay) {
    this.unPay = unPay;
  }

  public Integer getPayed() {
    return payed;
  }

  public void setPayed(Integer payed) {
    this.payed = payed;
  }

  public Integer getConsignment() {
    return consignment;
  }

  public void setConsignment(Integer consignment) {
    this.consignment = consignment;
  }

  public Integer getSuccess() {
    return success;
  }

  public void setSuccess(Integer success) {
    this.success = success;
  }

  @Override
  public String toString() {
    return "OrderCountVO{" +
            "allCount=" + allCount +
            ", unPay=" + unPay +
            ", payed=" + payed +
            ", consignment=" + consignment +
            ", success=" + success +
            '}';
  }
}
