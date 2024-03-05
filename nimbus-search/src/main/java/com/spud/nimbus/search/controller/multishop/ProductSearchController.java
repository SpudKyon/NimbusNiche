package com.spud.nimbus.search.controller.multishop;

import com.spud.nimbus.api.search.dto.EsPageDTO;
import com.spud.nimbus.api.search.dto.ProductSearchDTO;
import com.spud.nimbus.api.search.vo.EsPageVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.search.constant.SearchTypeEnum;
import com.spud.nimbus.search.manager.ProductSearchManager;
import com.spud.nimbus.search.vo.SpuAdminVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author spud
 * @date 2024/2/26
 */
@RestController("multishopSearchSpuController")
@RequestMapping("/m/search")
@Tag(name = "multishop-spu管理列表接口")
public class ProductSearchController {

	private final ProductSearchManager productSearchManager;

	@Autowired
	public ProductSearchController(ProductSearchManager productSearchManager) {
		this.productSearchManager = productSearchManager;
	}

	@GetMapping("/page")
	@Operation(summary = "商品信息列表", description = "商品信息列表")
	public Result<EsPageVO<SpuAdminVO>> page(@Valid EsPageDTO pageDTO, ProductSearchDTO productSearchDTO) {
		Long shopId = AuthUserContext.get().getTenantId();
		productSearchDTO.setSearchType(SearchTypeEnum.MULTISHOP.value());
		productSearchDTO.setShopId(shopId);
		EsPageVO<SpuAdminVO> searchPage = productSearchManager.adminPage(pageDTO, productSearchDTO);
		return Result.success(searchPage);
	}

}
