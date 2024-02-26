package com.spud.nimbus.search.controller.platform;

import com.spud.nimbus.api.search.dto.EsPageDTO;
import com.spud.nimbus.api.search.dto.ProductSearchDTO;
import com.spud.nimbus.api.search.vo.EsPageVO;
import com.spud.nimbus.common.response.Result;
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
@RestController("platformSearchSpuController")
@RequestMapping("/p/search")
@Tag(name = "platform-spu列表接口")
public class ProductSearchController {

  @Autowired
  private ProductSearchManager productSearchManager;

  @GetMapping("/page")
  @Operation(summary = "商品管理信息列表（平台端）", description = "商品管理信息列表（平台端）")
  public Result<EsPageVO<SpuAdminVO>> adminPage(@Valid EsPageDTO pageDTO, ProductSearchDTO productSearchDTO) {
    productSearchDTO.setSearchType(SearchTypeEnum.PLATFORM.value());
    EsPageVO<SpuAdminVO> searchPage = productSearchManager.adminPage(pageDTO, productSearchDTO);
    return Result.success(searchPage);
  }
}
