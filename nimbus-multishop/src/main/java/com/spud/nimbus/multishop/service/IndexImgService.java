package com.spud.nimbus.multishop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.multishop.dto.IndexImgDTO;
import com.spud.nimbus.multishop.model.IndexImg;
import com.spud.nimbus.multishop.vo.IndexImgVO;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/24
 */
public interface IndexImgService extends IService<IndexImg> {

	/**
	 * 分页获取轮播图列表
	 * @param pageDTO 分页参数
	 * @param indexImgDTO
	 * @return 轮播图列表分页数据
	 */
	PageVO<IndexImgVO> page(PageDTO pageDTO, IndexImgDTO indexImgDTO);

	/**
	 * 根据轮播图id获取轮播图
	 * @param imgId 轮播图id
	 * @return 轮播图
	 */
	IndexImgVO getByImgId(Long imgId);

	/**
	 * 保存轮播图
	 * @param indexImg 轮播图
	 */
	boolean save(IndexImg indexImg);

	/**
	 * 更新轮播图
	 * @param indexImg 轮播图
	 */
	void update(IndexImg indexImg);

	/**
	 * 根据轮播图id删除轮播图
	 * @param imgId
	 * @param shopId
	 */
	void deleteById(Long imgId, Long shopId);

	/**
	 * 根据店铺id，获取轮播图列表
	 * @param shopId
	 * @return
	 */
	List<IndexImgVO> getListByShopId(Long shopId);

	/**
	 * 根据商品d删除轮播图信息
	 * @param spuId
	 * @param shopId
	 */
	void deleteBySpuId(Long spuId, Long shopId);

}
