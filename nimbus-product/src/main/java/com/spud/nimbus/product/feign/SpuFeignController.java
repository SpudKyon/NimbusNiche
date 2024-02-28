package com.spud.nimbus.product.feign;

import com.spud.nimbus.api.product.feign.SpuFeignClient;
import com.spud.nimbus.api.product.vo.SkuVO;
import com.spud.nimbus.api.product.vo.SpuAndSkuVO;
import com.spud.nimbus.api.product.vo.SpuVO;
import com.spud.nimbus.common.constant.StatusEnum;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.response.ResultCode;
import com.spud.nimbus.product.service.SkuService;
import com.spud.nimbus.product.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/10
 */
@RestController
public class SpuFeignController implements SpuFeignClient {

	@Autowired
	private SpuService spuService;

	@Autowired
	private SkuService skuService;

	@Override
	public Result<SpuVO> getById(Long spuId) {
		SpuVO spuVO = spuService.getBySpuId(spuId);
		SpuVO spu = new SpuVO();
		spu.setSpuId(spuVO.getSpuId());
		spu.setName(spuVO.getName());
		spu.setMainImgUrl(spuVO.getMainImgUrl());
		return Result.success(spu);
	}

	@Override
	public Result<SpuAndSkuVO> getSpuAndSkuById(Long spuId, Long skuId) {
		SpuVO spu = spuService.getBySpuId(spuId);
		SkuVO sku = skuService.getSkuBySkuId(skuId);

		// 当商品状态不正常时，不能添加到购物车
		boolean spuIsNotExist = Objects.isNull(spu) || Objects.isNull(sku)
				|| !Objects.equals(spu.getStatus(), StatusEnum.ENABLE.value())
				|| !Objects.equals(sku.getStatus(), StatusEnum.ENABLE.value())
				|| !Objects.equals(sku.getSpuId(), spu.getSpuId());
		if (spuIsNotExist) {
			// 当返回商品不存在时，前端应该将商品从购物车界面移除
			return Result.fail(ResultCode.SPU_NOT_EXIST, null);
		}

		SpuAndSkuVO spuAndSku = new SpuAndSkuVO();
		spuAndSku.setSku(sku);
		spuAndSku.setSpu(spu);
		return Result.success(spuAndSku);
	}

	@Override
	public Result<List<SpuVO>> getSpusBySpuIds(List<Long> spuIds, String spuName, Integer isFailure) {
		return Result.success(spuService.listBySpuIds(spuIds, spuName, isFailure));
	}

}