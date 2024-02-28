package com.spud.nimbus.product.controller.app;

import com.spud.nimbus.api.product.vo.AttrVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.product.service.AttrService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author spud
 * @date 2024/2/13
 */
@RestController("appAttrController")
@RequestMapping("v1/ua/attr")
@Tag(name = "app-属性信息")
public class AttrController {

	@Autowired
	private AttrService attrService;

	@GetMapping
	@Operation(summary = "获取属性信息", description = "根据attrId获取属性信息")
	public Result<AttrVO> getByAttrId(@RequestParam Long attrId) {
		return Result.success(attrService.getByAttrId(attrId));
	}

}