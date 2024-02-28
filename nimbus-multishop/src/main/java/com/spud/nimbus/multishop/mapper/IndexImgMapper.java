package com.spud.nimbus.multishop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spud.nimbus.multishop.dto.IndexImgDTO;
import com.spud.nimbus.multishop.model.IndexImg;
import com.spud.nimbus.multishop.vo.IndexImgVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 轮播图 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Mapper
public interface IndexImgMapper extends BaseMapper<IndexImg> {

	/**
	 * 获取轮播图列表
	 * @param indexImgDTO
	 * @return 轮播图列表
	 */
	List<IndexImgVO> list(@Param("indexImg") IndexImgDTO indexImgDTO);

	/**
	 * 根据轮播图id获取轮播图
	 * @param imgId 轮播图id
	 * @return 轮播图
	 */
	IndexImgVO getByImgId(@Param("imgId") Long imgId);

	/**
	 * 保存轮播图
	 * @param indexImg 轮播图
	 */
	void save(@Param("indexImg") IndexImg indexImg);

	/**
	 * 更新轮播图
	 * @param indexImg 轮播图
	 */
	void update(@Param("indexImg") IndexImg indexImg);

	/**
	 * 根据轮播图id删除轮播图
	 * @param imgId 轮播图id
	 * @param shopId 店铺id
	 */
	void deleteByIdAndShopId(@Param("imgId") Long imgId, @Param("shopId") Long shopId);

	/**
	 * 根据店铺id，获取轮播图列表
	 * @param shopId
	 * @return
	 */
	List<IndexImgVO> getListByShopId(@Param("shopId") Long shopId);

	/**
	 * 根据spuId清除轮播图的spuId
	 * @param spuId
	 */
	void clearSpuIdBySpuId(Long spuId);

}
