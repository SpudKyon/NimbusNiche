package com.spud.nimbus.payment.controller;

import cn.hutool.core.util.StrUtil;
import com.spud.nimbus.payment.bo.PayInfoResultBO;
import com.spud.nimbus.payment.model.PayInfo;
import com.spud.nimbus.payment.service.PayInfoService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author spud
 * @date 2024/2/26
 */
@Hidden
@RestController
@RequestMapping("/notice/pay")
public class PayNoticeController {

	private final PayInfoService payInfoService;

	@Autowired
	public PayNoticeController(PayInfoService payInfoService) {
		this.payInfoService = payInfoService;
	}

	/**
	 * 支付异步回调
	 */
	@RequestMapping("/order")
	public ResponseEntity<String> submit(Long payId) {
		PayInfo payInfo = payInfoService.getByPayId(payId);
		String[] orderIdStrArr = payInfo.getOrderIds().split(StrUtil.COMMA);
		List<Long> orderIdList = new ArrayList<>();
		for (String s : orderIdStrArr) {
			orderIdList.add(Long.valueOf(s));
		}
		PayInfoResultBO payInfoResult = new PayInfoResultBO();
		payInfoResult.setPayId(payId);
		payInfoResult.setBizPayNo(payInfo.getBizPayNo());
		payInfoResult.setCallbackContent(payInfo.getCallbackContent());
		// 支付成功
		payInfoService.paySuccess(payInfoResult, orderIdList);
		return ResponseEntity.ok("");
	}

}
