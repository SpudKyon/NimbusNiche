package com.spud.nimbus.search.controller.app;

import cn.hutool.core.collection.CollUtil;
import com.spud.nimbus.api.multishop.bo.EsShopDetailBO;
import com.spud.nimbus.api.multishop.feign.ShopDetailFeignClient;
import com.spud.nimbus.api.search.dto.EsPageDTO;
import com.spud.nimbus.api.search.dto.ProductSearchDTO;
import com.spud.nimbus.api.search.vo.EsPageVO;
import com.spud.nimbus.api.search.vo.search.ProductSearchVO;
import com.spud.nimbus.api.search.vo.search.ShopInfoSearchVO;
import com.spud.nimbus.common.constant.StatusEnum;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.response.ResultCode;
import com.spud.nimbus.search.manager.ProductSearchManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/26
 */
@RestController("appSearchSpuController")
@RequestMapping("/ua/search")
@Tag(name = "app-spu搜索接口")
public class ProductSearchController {

	@Autowired
	private ProductSearchManager productSearchManager;

	@Autowired
	private ShopDetailFeignClient shopDetailFeignClient;

	@GetMapping("/page")
	@Operation(summary = "商品信息列表-包含spu、品牌、分类、属性和店铺信息", description = "spu列表-包含spu、品牌、分类、属性和店铺信息")
	public Result<EsPageVO<ProductSearchVO>> page(@Valid EsPageDTO pageDTO, ProductSearchDTO productSearchDTO) {
		productSearchDTO.setSpuStatus(StatusEnum.ENABLE.value());
		EsPageVO<ProductSearchVO> searchPage = productSearchManager.page(pageDTO, productSearchDTO);
		loadShopData(searchPage.getList());
		return Result.success(searchPage);
	}

	@GetMapping("/simple_page")
	@Operation(summary = "商品信息列表-包含spu信息", description = "商品信息列表-包含spu信息")
	public Result<EsPageVO<ProductSearchVO>> simplePage(@Valid EsPageDTO pageDTO, ProductSearchDTO productSearchDTO) {
		productSearchDTO.setSpuStatus(StatusEnum.ENABLE.value());
		EsPageVO<ProductSearchVO> searchPage = productSearchManager.simplePage(pageDTO, productSearchDTO);
		loadShopData(searchPage.getList());
		return Result.success(searchPage);
	}

	/**
	 * 获取店铺数据
	 * @param list
	 */
	private void loadShopData(List<ProductSearchVO> list) {
		if (CollUtil.isEmpty(list)) {
			return;
		}
		for (ProductSearchVO productSearchVO : list) {
			if (Objects.isNull(productSearchVO.getShopInfo())
					|| Objects.isNull(productSearchVO.getShopInfo().getShopId())) {
				continue;
			}
			Result<EsShopDetailBO> shopDataResponse = shopDetailFeignClient
					.shopExtensionData(productSearchVO.getShopInfo().getShopId());
			if (Objects.equals(shopDataResponse.getCode(), ResultCode.OK.value())) {
				EsShopDetailBO esShopDetailBO = shopDataResponse.getData();
				ShopInfoSearchVO shopInfo = productSearchVO.getShopInfo();
				shopInfo.setShopLogo(esShopDetailBO.getShopLogo());
				shopInfo.setShopName(esShopDetailBO.getShopName());
				shopInfo.setType(esShopDetailBO.getType());
			}
		}
	}

}
