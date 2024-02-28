package com.spud.nimbus.product.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.api.product.vo.SpuAttrValueVO;
import com.spud.nimbus.common.cache.constant.CacheNames;
import com.spud.nimbus.common.cache.util.RedisUtil;
import com.spud.nimbus.product.mapper.SpuAttrValueMapper;
import com.spud.nimbus.product.mapper.SpuMapper;
import com.spud.nimbus.product.model.SpuAttrValue;
import com.spud.nimbus.product.service.SpuAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品规格属性关联信息 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class SpuAttrValueServiceImpl extends ServiceImpl<SpuAttrValueMapper, SpuAttrValue>
		implements SpuAttrValueService {

	@Autowired
	private SpuAttrValueMapper spuAttrValueMapper;

	@Autowired
	private SpuMapper spuMapper;

	@Override
	public void update(Long spuId, List<SpuAttrValue> spuAttrValues, List<SpuAttrValueVO> spuAttrValuesDb) {
		List<Long> spuAttrValueIdsDb = spuAttrValuesDb.stream().map(SpuAttrValueVO::getSpuAttrValueId)
				.collect(Collectors.toList());
		List<SpuAttrValue> updateList = new ArrayList<>();
		List<SpuAttrValue> saveList = new ArrayList<>();
		List<Long> spuAttrValueIds = new ArrayList<>();
		for (SpuAttrValue spuAttrValue : spuAttrValues) {
			if (spuAttrValueIdsDb.contains(spuAttrValue.getSpuAttrValueId())) {
				if (Objects.nonNull(spuAttrValue.getAttrValueName())
						|| Objects.nonNull(spuAttrValue.getAttrValueId())) {
					updateList.add(spuAttrValue);
				}
				spuAttrValueIds.add(spuAttrValue.getSpuAttrValueId());
				continue;
			}
			spuAttrValue.setSpuId(spuId);
			saveList.add(spuAttrValue);
		}
		// 保存新增的关联属性
		if (CollUtil.isNotEmpty(saveList)) {
			saveBatch(spuId, saveList);
		}
		// 更新属性
		if (CollUtil.isNotEmpty(updateList)) {
			spuAttrValueMapper.updateBatch(updateList);
		}
		// 删除属性
		spuAttrValueIdsDb.removeAll(spuAttrValueIds);
		if (CollUtil.isNotEmpty(spuAttrValueIdsDb)) {
			spuAttrValueMapper.deleteBatch(spuAttrValueIdsDb);
		}
	}

	@Override
	public void saveBatch(Long spuId, List<SpuAttrValue> spuAttrValues) {
		if (CollUtil.isEmpty(spuAttrValues)) {
			return;
		}
		for (SpuAttrValue spuAttrValue : spuAttrValues) {
			spuAttrValue.setSpuId(spuId);
		}
		spuAttrValueMapper.saveBatch(spuAttrValues);
	}

	@Override
	public void deleteBySpuId(Long spuId) {
		spuAttrValueMapper.deleteBySpuId(spuId);
	}

	@Override
	public void deleteByAttIdAndCategoryIds(Long attrId, List<Long> attrValueId, List<Long> categoryIds) {
		if (CollUtil.isEmpty(attrValueId) && CollUtil.isEmpty(categoryIds)) {
			return;
		}
		updateSpu(attrValueId, categoryIds);
		spuAttrValueMapper.deleteByAttIdAndCategoryIds(attrId, attrValueId, categoryIds);
	}

	@Override
	public List<SpuAttrValueVO> getSpuAttrsBySpuId(Long spuId) {
		return spuAttrValueMapper.getSpuAttrsBySpuId(spuId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void batchUpdateSpuAttrValue(List<SpuAttrValue> spuAttrValues) {
		spuAttrValueMapper.batchUpdateSpuAttrValue(spuAttrValues);
		List<Long> attrValueIds = spuAttrValues.stream().map(SpuAttrValue::getAttrValueId).collect(Collectors.toList());
		updateSpu(attrValueIds, null);
	}

	private void updateSpu(List<Long> attrValueIds, List<Long> categoryIds) {
		List<Long> spuIds = null;
		if (CollUtil.isNotEmpty(attrValueIds)) {
			spuIds = spuAttrValueMapper.getShopIdByAttrValueIds(attrValueIds);
			spuMapper.updateSpuUpdateTime(spuIds, null);
		}
		else if (CollUtil.isNotEmpty(categoryIds)) {
			spuIds = spuMapper.getSpuIdsBySpuUpdateDTO(null, categoryIds, null, null);
			spuMapper.updateSpuUpdateTime(null, categoryIds);
		}
		if (CollUtil.isEmpty(spuIds)) {
			return;
		}
		RedisUtil.deleteBatch(CacheNames.SPU_KEY, spuIds);
		RedisUtil.deleteBatch(CacheNames.SPU_EXTENSION_KEY, spuIds);
	}

}
