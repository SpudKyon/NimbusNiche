package com.spud.nimbus.platform.controller;

import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.platform.model.SysConfig;
import com.spud.nimbus.platform.service.SysConfigService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author spud
 * @date 2024/2/25
 */
@RestController
@RequestMapping("/sys_config")
public class SysConfigController {

	private final SysConfigService sysConfigService;

	@Autowired
	public SysConfigController(SysConfigService sysConfigService) {
		this.sysConfigService = sysConfigService;
	}

	/**
	 * 获取保存支付宝支付配置信息
	 */
	@GetMapping("/info/{key}")
	public Result<String> info(@PathVariable("key") String key) {
		return Result.success(sysConfigService.getValue(key));
	}

	/**
	 * 保存配置
	 */
	@PostMapping("/save")
	public Result<Void> save(@RequestBody @Valid SysConfig sysConfig) {
		sysConfigService.saveOrUpdateSysConfig(sysConfig);
		return Result.success(null);
	}

}
