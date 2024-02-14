package com.spud.nimbus.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.util.PageUtil;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.product.mapper.SpuExtensionMapper;
import com.spud.nimbus.product.model.SpuExtension;
import com.spud.nimbus.product.service.SpuExtensionService;
import com.spud.nimbus.product.vo.SpuExtensionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class SpuExtensionServiceImpl extends ServiceImpl<SpuExtensionMapper, SpuExtension> implements SpuExtensionService {

  @Autowired
  private SpuExtensionMapper spuExtensionMapper;

  @Override
  public PageVO<SpuExtensionVO> page(PageDTO pageDTO) {
    return PageUtil.doPage(pageDTO, () -> spuExtensionMapper.list());
  }

  @Override
  public SpuExtensionVO getBySpuExtendId(Long spuExtendId) {
    return spuExtensionMapper.getBySpuExtendId(spuExtendId);
  }

  @Override
  public boolean save(SpuExtension spuExtension) {
    spuExtensionMapper.save(spuExtension);
    return false;
  }

  @Override
  public void updateStock(Long spuId, Integer count) {
    spuExtensionMapper.updateStock(spuId,count);
  }

  @Override
  public void deleteById(Long spuId) {
    spuExtensionMapper.deleteById(spuId);
  }

  @Override
  public SpuExtension getBySpuId(Long spuId) {
    return spuExtensionMapper.getBySpuId(spuId);
  }
}
