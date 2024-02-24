package com.spud.nimbus.multishop.controller.admin;

import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.common.util.BeanUtil;
import com.spud.nimbus.multishop.dto.HotSearchDTO;
import com.spud.nimbus.multishop.model.HotSearch;
import com.spud.nimbus.multishop.service.HotSearchService;
import com.spud.nimbus.multishop.vo.HotSearchVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.spud.nimbus.common.database.dto.PageDTO;

/**
 * @author spud
 * @date 2024/2/24
 */
@RestController("adminHotSearchController")
@RequestMapping("/admin/hot_search")
@Tag(name = "admin-热搜")
public class HotSearchController {

  @Autowired
  private HotSearchService hotSearchService;


  @GetMapping("/page")
  @Operation(summary = "分页获取热搜列表", description = "分页获取热搜列表")
  public Result<PageVO<HotSearchVO>> page(@Valid PageDTO pageDTO, HotSearchDTO hotSearchDTO) {
    hotSearchDTO.setShopId(AuthUserContext.get().getTenantId());
    PageVO<HotSearchVO> hotSearchPage = hotSearchService.page(pageDTO, hotSearchDTO);
    return Result.success(hotSearchPage);
  }

  @GetMapping
  @Operation(summary = "获取热搜", description = "根据hotSearchId获取热搜")
  public Result<HotSearchVO> getByHotSearchId(@RequestParam Long hotSearchId) {
    return Result.success(hotSearchService.getByHotSearchId(hotSearchId));
  }

  @PostMapping
  @Operation(summary = "保存热搜", description = "保存热搜")
  public Result<Void> save(@Valid @RequestBody HotSearchDTO hotSearchDTO) {
    HotSearch hotSearch = BeanUtil.map(hotSearchDTO, HotSearch.class);
    hotSearch.setShopId(AuthUserContext.get().getTenantId());
    hotSearchService.save(hotSearch);
    hotSearchService.removeHotSearchListCache(hotSearch.getShopId());
    return Result.success(null);
  }

  @PutMapping
  @Operation(summary = "更新热搜", description = "更新热搜")
  public Result<Void> update(@Valid @RequestBody HotSearchDTO hotSearchDTO) {
    HotSearch hotSearch = BeanUtil.map(hotSearchDTO, HotSearch.class);
    hotSearch.setShopId(AuthUserContext.get().getTenantId());
    hotSearchService.update(hotSearch);
    hotSearchService.removeHotSearchListCache(hotSearch.getShopId());
    return Result.success(null);
  }

  @DeleteMapping
  @Operation(summary = "删除热搜", description = "根据热搜id删除热搜")
  public Result<Void> delete(@RequestParam Long hotSearchId) {
    Long shopId = AuthUserContext.get().getTenantId();
    hotSearchService.deleteById(hotSearchId, shopId);
    hotSearchService.removeHotSearchListCache(shopId);
    return Result.success(null);
  }
}
