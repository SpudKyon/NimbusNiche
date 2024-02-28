package com.spud.nimbus.order.vo;

import com.spud.nimbus.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author spud
 * @date 2024/2/25
 */
@Data
@ToString
public class SubmitOrderPayInfoVO extends BaseVO {

	@Schema(description = "商品名称")
	private List<String> spuNameList;

	@Schema(description = "收货人姓名")
	private String consignee;

	@Schema(description = "收货地址")
	private String userAddr;

	@Schema(description = "收货人手机号")
	private String mobile;

	@Schema(description = "订单过期时间")
	private Date endTime;

	@Schema(description = "总共需要支付金额")
	private Long totalFee;

}
