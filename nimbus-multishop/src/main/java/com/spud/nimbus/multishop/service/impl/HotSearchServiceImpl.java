package com.spud.nimbus.multishop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.common.cache.constant.CacheNames;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.util.PageUtil;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.multishop.dto.HotSearchDTO;
import com.spud.nimbus.multishop.mapper.HotSearchMapper;
import com.spud.nimbus.multishop.model.HotSearch;
import com.spud.nimbus.multishop.vo.HotSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.spud.nimbus.multishop.service.HotSearchService;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/24
 */
@Service
public class HotSearchServiceImpl extends ServiceImpl<HotSearchMapper, HotSearch> implements HotSearchService {

	private final HotSearchMapper hotSearchMapper;

	@Autowired
	public HotSearchServiceImpl(HotSearchMapper hotSearchMapper) {
		this.hotSearchMapper = hotSearchMapper;
	}

	@Override
	public PageVO<HotSearchVO> page(PageDTO pageDTO, HotSearchDTO hotSearchDTO) {
		return PageUtil.doPage(pageDTO, () -> hotSearchMapper.list(hotSearchDTO));
	}

	@Override
	public HotSearchVO getByHotSearchId(Long hotSearchId) {
		return hotSearchMapper.getByHotSearchId(hotSearchId);
	}

	@Override
	public boolean save(HotSearch hotSearch) {
		return super.save(hotSearch);
	}

	@Override
	public void update(HotSearch hotSearch) {
		hotSearchMapper.update(hotSearch);
	}

	@Override
	public void deleteById(Long hotSearchId, Long shopId) {
		hotSearchMapper.deleteById(hotSearchId, shopId);
	}

	@Override
	@Cacheable(cacheNames = CacheNames.HOT_SEARCH_LIST_KEY, key = "#shopId")
	public List<HotSearchVO> listByShopId(Long shopId) {
		return hotSearchMapper.listByShopId(shopId);
	}

	@Override
	@CacheEvict(cacheNames = CacheNames.HOT_SEARCH_LIST_KEY, key = "#shopId")
	public void removeHotSearchListCache(Long shopId) {
	}

}
