package com.spud.nimbus.product.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.api.product.vo.SkuVO;
import com.spud.nimbus.product.dto.SkuDTO;
import com.spud.nimbus.product.mapper.SkuStockMapper;
import com.spud.nimbus.product.model.SkuStock;
import com.spud.nimbus.product.service.SkuStockService;
import com.spud.nimbus.product.vo.SkuStockVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 库存信息 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class SkuStockServiceImpl extends ServiceImpl<SkuStockMapper, SkuStock> implements SkuStockService {

  @Autowired
  private SkuStockMapper skuStockMapper;

  @Override
  public boolean save(SkuStock skuStock) {
    skuStockMapper.save(skuStock);
    return true;
  }

  @Override
  public void update(SkuStock skuStock) {
    skuStockMapper.update(skuStock);
  }

  @Override
  public void deleteById(Long stockId) {
    skuStockMapper.deleteById(stockId);
  }

  @Override
  public void saveBatch(List<SkuStock> skuStocks) {
    skuStockMapper.saveBatch(skuStocks);
  }

  @Override
  public void deleteBySkuIds(List<Long> skuIds) {
    skuStockMapper.deleteBySkuIds(skuIds);
  }

  @Override
  public List<SkuStockVO> listBySkuList(List<SkuVO> skuVOList) {
    return skuStockMapper.listBySkuList(skuVOList);
  }

  @Override
  public void updateBatch(List<SkuDTO> skuList) {
    if (CollUtil.isEmpty(skuList)) {
      return;
    }
    // 如果是修改库存，此时不需要改变锁定库存
    List<SkuStock> skuStocks = new ArrayList<>();
    for (SkuDTO sku : skuList) {
      SkuStock skuStock = new SkuStock();
      if (Objects.nonNull(sku.getChangeStock()) && sku.getChangeStock() > 0) {
        skuStock.setStock(sku.getChangeStock());
        skuStock.setSkuId(sku.getSkuId());
        skuStocks.add(skuStock);
      }
    }
    if (CollUtil.isNotEmpty(skuStocks)) {
      skuStockMapper.updateStock(skuStocks);
    }
  }
}