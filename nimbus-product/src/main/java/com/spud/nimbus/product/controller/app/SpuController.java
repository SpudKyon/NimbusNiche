package com.spud.nimbus.product.controller.app;

import com.spud.nimbus.api.product.vo.SpuVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.util.BeanUtil;
import com.spud.nimbus.product.model.SpuExtension;
import com.spud.nimbus.product.service.SkuService;
import com.spud.nimbus.product.service.SpuService;
import com.spud.nimbus.product.vo.app.SkuAppVO;
import com.spud.nimbus.product.vo.app.SpuAppVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/13
 */
@RestController("appSpuController")
@RequestMapping("v1/ua/spu")
@Tag(name = "app-spu信息")
public class SpuController {

	@Autowired
	private SpuService spuService;

	@Autowired
	private SkuService skuService;

	@GetMapping("/prod_info")
	@Operation(summary = "商品详情信息", description = "根据商品ID（prodId）获取商品信息")
	@Parameter(name = "spuId", description = "商品ID", required = true)
	public Result<SpuAppVO> prodInfo(@RequestParam("spuId") Long spuId) {

		SpuVO spu = spuService.getBySpuId(spuId);
		SpuAppVO spuAppVO = BeanUtil.map(spu, SpuAppVO.class);
		SpuExtension spuExtension = spuService.getSpuExtension(spuId);
		spuAppVO.setTotalStock(spuExtension.getActualStock());
		spuAppVO.setSaleNum(spuExtension.getSaleNum());
		List<SkuAppVO> skuAppVO = skuService.getSkuBySpuId(spu.getSpuId());
		spuAppVO.setSkus(skuAppVO);
		return Result.success(spuAppVO);
	}

}
