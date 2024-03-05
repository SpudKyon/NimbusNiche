package com.spud.nimbus.auth.controller;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.spud.nimbus.common.response.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author spud
 * @date 2024/2/17
 */
@RestController
@RequestMapping("v1/ua/captcha")
@Tag(name = "验证码")
public class CaptchaController {

	private final CaptchaService captchaService;

	@Autowired
	public CaptchaController(CaptchaService captchaService) {
		this.captchaService = captchaService;
	}

	@PostMapping({ "/get" })
	public Result<ResponseModel> get(@RequestBody CaptchaVO captchaVO) {
		return Result.success(captchaService.get(captchaVO));
	}

	@PostMapping({ "/check" })
	public Result<ResponseModel> check(@RequestBody CaptchaVO captchaVO) {
		return Result.success(captchaService.check(captchaVO));
	}

}
