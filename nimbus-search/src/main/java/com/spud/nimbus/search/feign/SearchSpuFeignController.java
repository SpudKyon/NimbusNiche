package com.spud.nimbus.search.feign;

import cn.hutool.core.collection.CollUtil;
import com.spud.nimbus.api.search.dto.EsPageDTO;
import com.spud.nimbus.api.search.dto.ProductSearchDTO;
import com.spud.nimbus.api.search.feign.SearchSpuFeignClient;
import com.spud.nimbus.api.search.vo.EsPageVO;
import com.spud.nimbus.api.search.vo.search.ProductSearchVO;
import com.spud.nimbus.api.search.vo.search.SpuSearchVO;
import com.spud.nimbus.common.constant.Constant;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.search.manager.ProductSearchManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/26
 */
@RestController
public class SearchSpuFeignController implements SearchSpuFeignClient {

	@Autowired
	private ProductSearchManager productSearchManager;

	@Override
	public Result<EsPageVO<ProductSearchVO>> search(EsPageDTO pageDTO, ProductSearchDTO productSearchDTO) {
		return Result.success(productSearchManager.simplePage(pageDTO, productSearchDTO));
	}

	@Override
	public Result<List<SpuSearchVO>> getSpusBySpuIds(List<Long> spuIds) {
		if (CollUtil.isEmpty(spuIds)) {
			return Result.success(new ArrayList<>());
		}
		ProductSearchDTO productSearchDTO = new ProductSearchDTO();
		productSearchDTO.setSpuIds(spuIds);
		List<SpuSearchVO> list = productSearchManager.list(productSearchDTO);
		return Result.success(list);
	}

	@Override
	public Result<EsPageVO<ProductSearchVO>> spuPage(Integer pageNum, Integer pageSize, Long shopId) {
		EsPageDTO pageDTO = new EsPageDTO();
		pageDTO.setPageNum(pageNum);
		pageDTO.setPageSize(pageSize);
		ProductSearchDTO productSearchDTO = new ProductSearchDTO();
		// 平台id则搜索整个平台的商品
		if (!Objects.equals(shopId, Constant.PLATFORM_SHOP_ID)) {
			productSearchDTO.setShopId(shopId);
		}
		EsPageVO<ProductSearchVO> page = productSearchManager.page(pageDTO, productSearchDTO);
		return Result.success(page);
	}

	@Override
	public Result<List<SpuSearchVO>> limitSizeListByShopIds(List<Long> shopIds, Integer size) {
		if (CollUtil.isEmpty(shopIds)) {
			return Result.success(new ArrayList<>());
		}
		List<SpuSearchVO> list = productSearchManager.limitSizeListByShopIds(shopIds, size);
		return Result.success(list);
	}

}
