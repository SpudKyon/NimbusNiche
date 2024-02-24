package com.spud.nimbus.multishop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.multishop.dto.HotSearchDTO;
import com.spud.nimbus.multishop.model.HotSearch;
import com.spud.nimbus.multishop.vo.HotSearchVO;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/24
 */
public interface HotSearchService  extends IService<HotSearch> {

	/**
	 * 分页获取热搜列表
	 * @param pageDTO 分页参数
	 * @param hotSearchDTO 搜索参数
	 * @return 热搜列表分页数据
	 */
	PageVO<HotSearchVO> page(PageDTO pageDTO, HotSearchDTO hotSearchDTO);

	/**
	 * 根据热搜id获取热搜
	 *
	 * @param hotSearchId 热搜id
	 * @return 热搜
	 */
	HotSearchVO getByHotSearchId(Long hotSearchId);

	/**
	 * 保存热搜
	 * @param hotSearch 热搜
	 */
	boolean save(HotSearch hotSearch);

	/**
	 * 更新热搜
	 * @param hotSearch 热搜
	 */
	void update(HotSearch hotSearch);

	/**
	 * 根据热搜id删除热搜
	 * @param hotSearchId 热搜id
	 * @param shopId 店铺id
	 */
	void deleteById(Long hotSearchId, Long shopId);

	/**
	 * 获取热搜列表
	 * @param shopId
	 * @return
	 */
	List<HotSearchVO> listByShopId(Long shopId);

	/**
	 * 清除热搜列表缓存
	 * @param shopId
	 */
	void removeHotSearchListCache(Long shopId);
}
