package com.spud.nimbus.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spud.nimbus.api.user.vo.AreaVO;
import com.spud.nimbus.user.model.Area;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 省市区地区信息 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-24
 */
@Mapper
public interface AreaMapper extends BaseMapper<Area> {
  /**
   * 获取省市区地区信息列表
   *
   * @return 省市区地区信息列表
   */
  List<AreaVO> list();

  /**
   * 根据省市区地区信息id获取省市区地区信息
   *
   * @param areaId 省市区地区信息id
   * @return 省市区地区信息
   */
  AreaVO getByAreaId(@Param("areaId") Long areaId);

  /**
   * 保存省市区地区信息
   *
   * @param area 省市区地区信息
   */
  boolean save(@Param("area") Area area);

  /**
   * 更新省市区地区信息
   *
   * @param area 省市区地区信息
   */
  void update(@Param("area") Area area);

  /**
   * 根据省市区地区信息id删除省市区地区信息
   *
   * @param areaId
   */
  void deleteById(@Param("areaId") Long areaId);

  /**
   * 获取该地址id下的下级地址数量
   *
   * @param areaId
   * @return
   */
  int countByAreaId(@Param("areaId") Long areaId);

  /**
   * 根据上级分类id获取下级地址列表
   *
   * @param pid
   * @return
   */
  List<AreaVO> listByPid(@Param("pid") Long pid);

  /**
   * 获取省市区三级结构完整的集合
   *
   * @return 省市区三级结构完整的集合
   */
  List<AreaVO> getAreaListInfo();

  /**
   * 获取可用的省市区列表
   *
   * @return
   */
  List<AreaVO> listAreaOfEnable();
}
