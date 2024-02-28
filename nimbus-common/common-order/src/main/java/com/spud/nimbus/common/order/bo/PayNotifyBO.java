package com.spud.nimbus.common.order.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author spud
 * @date 2024/1/30
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PayNotifyBO {

	private List<Long> orderIds;

}