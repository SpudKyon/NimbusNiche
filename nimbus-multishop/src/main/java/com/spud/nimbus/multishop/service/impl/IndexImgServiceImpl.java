package com.spud.nimbus.multishop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.common.cache.constant.CacheNames;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.util.PageUtil;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.multishop.dto.IndexImgDTO;
import com.spud.nimbus.multishop.mapper.IndexImgMapper;
import com.spud.nimbus.multishop.model.IndexImg;
import com.spud.nimbus.multishop.service.IndexImgService;
import com.spud.nimbus.multishop.vo.IndexImgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 轮播图
 *
 * @author YXF
 * @date 2020-11-24 16:38:32
 */
@Service
public class IndexImgServiceImpl extends ServiceImpl<IndexImgMapper, IndexImg> implements IndexImgService {

	@Autowired
	private IndexImgMapper indexImgMapper;

	@Override
	public PageVO<IndexImgVO> page(PageDTO pageDTO, IndexImgDTO indexImgDTO) {
		return PageUtil.doPage(pageDTO, () -> indexImgMapper.list(indexImgDTO));
	}

	@Override
	public IndexImgVO getByImgId(Long imgId) {
		return indexImgMapper.getByImgId(imgId);
	}

	@Override
	@CacheEvict(cacheNames = CacheNames.INDEX_IMG_KEY, key = "#indexImg.shopId")
	public boolean save(IndexImg indexImg) {
		return super.save(indexImg);
	}

	@Override
	@CacheEvict(cacheNames = CacheNames.INDEX_IMG_KEY, key = "#indexImg.shopId")
	public void update(IndexImg indexImg) {
		indexImgMapper.update(indexImg);
	}

	@Override
	@CacheEvict(cacheNames = CacheNames.INDEX_IMG_KEY, key = "#shopId")
	public void deleteById(Long imgId, Long shopId) {
		indexImgMapper.deleteByIdAndShopId(imgId, shopId);
	}

	@Override
	@Cacheable(cacheNames = CacheNames.INDEX_IMG_KEY, key = "#shopId", sync = true)
	public List<IndexImgVO> getListByShopId(Long shopId) {
		List<IndexImgVO> listByShopId = indexImgMapper.getListByShopId(shopId);
		return listByShopId;
	}

	@Override
	@Caching(evict = { @CacheEvict(cacheNames = CacheNames.INDEX_IMG_KEY, key = "#shopId"),
			@CacheEvict(cacheNames = CacheNames.INDEX_IMG_KEY, key = "0") })
	public void deleteBySpuId(Long spuId, Long shopId) {
		indexImgMapper.clearSpuIdBySpuId(spuId);
	}

}
