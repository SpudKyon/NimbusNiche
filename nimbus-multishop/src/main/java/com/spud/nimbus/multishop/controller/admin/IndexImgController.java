package com.spud.nimbus.multishop.controller.admin;

import com.spud.nimbus.api.product.feign.SpuFeignClient;
import com.spud.nimbus.api.product.vo.SpuVO;
import com.spud.nimbus.common.constant.StatusEnum;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.common.util.BeanUtil;
import com.spud.nimbus.multishop.dto.IndexImgDTO;
import com.spud.nimbus.multishop.model.IndexImg;
import com.spud.nimbus.multishop.service.IndexImgService;
import com.spud.nimbus.multishop.vo.IndexImgVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/24
 */
@RestController("adminIndexImgController")
@RequestMapping("/admin/index_img")
@Tag(name = "admin-轮播图")
public class IndexImgController {

  @Autowired
  private IndexImgService indexImgService;

  @Autowired
  private SpuFeignClient spuFeignClient;

  @GetMapping("/page")
  @Operation(summary = "获取轮播图列表", description = "分页获取轮播图列表")
  public Result<PageVO<IndexImgVO>> page(@Valid PageDTO pageDTO, IndexImgDTO indexImgDTO) {
    indexImgDTO.setShopId(AuthUserContext.get().getTenantId());
    PageVO<IndexImgVO> indexImgPage = indexImgService.page(pageDTO, indexImgDTO);
    return Result.success(indexImgPage);
  }

  @GetMapping
  @Operation(summary = "获取轮播图", description = "根据imgId获取轮播图")
  public Result<IndexImgVO> getByImgId(@RequestParam Long imgId) {
    IndexImgVO indexImg = indexImgService.getByImgId(imgId);
    if (Objects.nonNull(indexImg.getSpuId())) {
      Result<SpuVO> spuResponse = spuFeignClient.getById(indexImg.getSpuId());
      indexImg.setSpu(spuResponse.getData());
    }
    return Result.success(indexImg);
  }

  @PostMapping
  @Operation(summary = "保存轮播图", description = "保存轮播图")
  public Result<Void> save(@Valid @RequestBody IndexImgDTO indexImgDTO) {
    IndexImg indexImg = BeanUtil.map(indexImgDTO, IndexImg.class);
    indexImg.setImgId(null);
    indexImg.setShopId(AuthUserContext.get().getTenantId());
    indexImg.setStatus(StatusEnum.ENABLE.value());
    indexImgService.save(indexImg);
    return Result.success(null);
  }

  @PutMapping
  @Operation(summary = "更新轮播图", description = "更新轮播图")
  public Result<Void> update(@Valid @RequestBody IndexImgDTO indexImgDTO) {
    IndexImg indexImg = BeanUtil.map(indexImgDTO, IndexImg.class);
    indexImg.setShopId(AuthUserContext.get().getTenantId());
    indexImgService.update(indexImg);
    return Result.success(null);
  }

  @DeleteMapping
  @Operation(summary = "删除轮播图", description = "根据轮播图id删除轮播图")
  public Result<Void> delete(@RequestParam Long imgId) {
    indexImgService.deleteById(imgId, AuthUserContext.get().getTenantId());
    return Result.success(null);
  }
}
