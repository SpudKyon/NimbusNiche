package com.spud.nimbus.payment.controller;

import com.spud.nimbus.api.auth.bo.UserInfoInTokenBO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.payment.bo.PayInfoBO;
import com.spud.nimbus.payment.dto.PayInfoDTO;
import com.spud.nimbus.payment.service.PayInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.spud.nimbus.common.util.BooleanUtil;

/**
 * @author spud
 * @date 2024/2/26
 */
@RestController
@RequestMapping("/pay")
@Tag(name = "订单接口")
public class PayController {

	@Autowired
	private PayInfoService payInfoService;

	@Autowired
	private PayNoticeController payNoticeController;

	/**
	 * 支付接口
	 */
	@PostMapping("/order")
	@Operation(summary = "根据订单号进行支付", description = "根据订单号进行支付")
	public Result<?> pay(HttpServletRequest request, @Valid @RequestBody PayInfoDTO payParam) {
		// 这里的地址是网关通过转发过来的时候，获取到当前服务器的地址，测试环境要用测试环境的uri
		String gatewayUri = "http://192.168.1.17:8126/nimbus_payment";
		UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
		Long userId = userInfoInTokenBO.getUserId();
		PayInfoBO payInfo = payInfoService.pay(userId, payParam);
		payInfo.setBizUserId(userInfoInTokenBO.getBizUserId());
		// 回调地址
		payInfo.setApiNoticeUrl(gatewayUri + "/notice/pay/order");
		payInfo.setReturnUrl(payParam.getReturnUrl());
		payNoticeController.submit(payInfo.getPayId());
		return Result.success(payInfo.getPayId());
	}

	@GetMapping("/isPay/{orderIds}")
	@Operation(summary = "根据订单号查询该订单是否已经支付", description = "根据订单号查询该订单是否已经支付")
	public ResponseEntity<Boolean> isPay(@PathVariable String orderIds) {
		Long userId = AuthUserContext.get().getUserId();
		payInfoService.getPayStatusByOrderIds(orderIds);
		Integer isPay = payInfoService.isPay(orderIds, userId);
		return ResponseEntity.ok(BooleanUtil.isTrue(isPay));
	}

}
