package com.spud.nimbus.multishop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spud.nimbus.multishop.dto.HotSearchDTO;
import com.spud.nimbus.multishop.model.HotSearch;
import com.spud.nimbus.multishop.vo.HotSearchVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 热搜 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Mapper
public interface HotSearchMapper extends BaseMapper<HotSearch> {
  /**
   * 获取热搜列表
   *
   * @param hotSearchDTO 搜索参数
   * @return 热搜列表
   */
  List<HotSearchVO> list(@Param("hotSearchDTO") HotSearchDTO hotSearchDTO);

  /**
   * 根据热搜id获取热搜
   *
   * @param hotSearchId 热搜id
   * @return 热搜
   */
  HotSearchVO getByHotSearchId(@Param("hotSearchId") Long hotSearchId);

  /**
   * 保存热搜
   *
   * @param hotSearch 热搜
   */
  void save(@Param("hotSearch") HotSearch hotSearch);

  /**
   * 更新热搜
   *
   * @param hotSearch 热搜
   */
  void update(@Param("hotSearch") HotSearch hotSearch);

  /**
   * 根据热搜id删除热搜
   *
   * @param hotSearchId
   * @param shopId
   */
  void deleteById(@Param("hotSearchId") Long hotSearchId, @Param("shopId") Long shopId);

  /**
   * 根据店铺id获取热搜列表
   *
   * @param shopId
   * @return
   */
  List<HotSearchVO> listByShopId(@Param("shopId") Long shopId);
}
