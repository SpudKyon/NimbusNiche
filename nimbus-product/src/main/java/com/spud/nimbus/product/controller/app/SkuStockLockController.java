package com.spud.nimbus.product.controller.app;

import com.spud.nimbus.api.product.dto.SkuStockLockDTO;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.util.BeanUtil;
import com.spud.nimbus.product.model.SkuStockLock;
import com.spud.nimbus.product.service.SkuStockLockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author spud
 * @date 2024/2/13
 */
@RestController("appSkuStockLockController")
@RequestMapping("v1/a/sku_stock_lock")
@Tag(name = "库存锁定信息")
public class SkuStockLockController {

  @Autowired
  private SkuStockLockService skuStockLockService;


  @GetMapping("/page")
  @Operation(summary = "获取库存锁定信息列表", description = "分页获取库存锁定信息列表")
  public Result<PageVO<SkuStockLock>> page(@Valid PageDTO pageDTO) {
    PageVO<SkuStockLock> skuStockLockPage = skuStockLockService.page(pageDTO);
    return Result.success(skuStockLockPage);
  }

  @GetMapping
  @Operation(summary = "获取库存锁定信息", description = "根据id获取库存锁定信息")
  public Result<SkuStockLock> getById(@RequestParam Long id) {
    return Result.success(skuStockLockService.getById(id));
  }

  @PostMapping
  @Operation(summary = "保存库存锁定信息", description = "保存库存锁定信息")
  public Result<Void> save(@Valid @RequestBody SkuStockLockDTO skuStockLockDTO) {
    SkuStockLock skuStockLock = BeanUtil.map(skuStockLockDTO, SkuStockLock.class);
    skuStockLockService.save(skuStockLock);
    return Result.success(null);
  }

  @PutMapping
  @Operation(summary = "更新库存锁定信息", description = "更新库存锁定信息")
  public Result<Void> update(@Valid @RequestBody SkuStockLockDTO skuStockLockDTO) {
    SkuStockLock skuStockLock = BeanUtil.map(skuStockLockDTO, SkuStockLock.class);
    skuStockLockService.update(skuStockLock);
    return Result.success(null);
  }

  @DeleteMapping
  @Operation(summary = "删除库存锁定信息", description = "根据库存锁定信息id删除库存锁定信息")
  public Result<Void> delete(@RequestParam Long id) {
    skuStockLockService.deleteById(id);
    return Result.success(null);
  }
}
