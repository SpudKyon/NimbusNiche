package com.spud.nimbus.api.order.bo;

import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/9
 */
@Data
@ToString
public class OrderStatusBO {

  private Long orderId;

  private Integer status;
}
