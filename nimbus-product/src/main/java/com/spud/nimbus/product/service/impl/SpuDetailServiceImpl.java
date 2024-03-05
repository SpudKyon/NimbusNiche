package com.spud.nimbus.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.product.mapper.SpuDetailMapper;
import com.spud.nimbus.product.model.SpuDetail;
import com.spud.nimbus.product.service.SpuDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品详情信息 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class SpuDetailServiceImpl extends ServiceImpl<SpuDetailMapper, SpuDetail> implements SpuDetailService {

	private final SpuDetailMapper spuDetailMapper;

	@Autowired
	public SpuDetailServiceImpl(SpuDetailMapper spuDetailMapper) {
		this.spuDetailMapper = spuDetailMapper;
	}

	@Override
	public boolean save(SpuDetail spuDetail) {
		spuDetailMapper.save(spuDetail);
		return false;
	}

	@Override
	public void update(SpuDetail spuDetail) {
		spuDetailMapper.update(spuDetail);
	}

	@Override
	public void deleteById(Long spuId) {
		spuDetailMapper.deleteById(spuId);
	}

}
