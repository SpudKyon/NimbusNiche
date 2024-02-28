package com.spud.nimbus.product.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.product.mapper.SpuSkuAttrValueMapper;
import com.spud.nimbus.product.model.SpuSkuAttrValue;
import com.spud.nimbus.product.service.SpuSkuAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品sku销售属性关联信息 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class SpuSkuAttrValueServiceImpl extends ServiceImpl<SpuSkuAttrValueMapper, SpuSkuAttrValue>
		implements SpuSkuAttrValueService {

	@Autowired
	private SpuSkuAttrValueMapper spuSkuAttrValueMapper;

	@Override
	public boolean save(SpuSkuAttrValue spuSkuAttrValue) {
		spuSkuAttrValueMapper.save(spuSkuAttrValue);
		return false;
	}

	@Override
	public void updateBatch(List<SpuSkuAttrValue> spuSkuAttrValues) {
		if (CollUtil.isNotEmpty(spuSkuAttrValues)) {
			spuSkuAttrValueMapper.updateBatch(spuSkuAttrValues);
		}
	}

	@Override
	public void deleteById(Long spuSkuAttrId) {
		spuSkuAttrValueMapper.deleteById(spuSkuAttrId);
	}

	@Override
	public void saveBatch(List<SpuSkuAttrValue> spuSkuAttrValues) {
		if (CollUtil.isEmpty(spuSkuAttrValues)) {
			return;
		}
		spuSkuAttrValueMapper.saveBatch(spuSkuAttrValues);
	}

	@Override
	public void updateBySpuId(Long spuId) {
		spuSkuAttrValueMapper.updateBySpuId(spuId);
	}

	@Override
	public void changeStatusBySkuId(List<Long> skuIds, Integer status) {
		spuSkuAttrValueMapper.changeStatusBySkuId(skuIds, status);
	}

}
