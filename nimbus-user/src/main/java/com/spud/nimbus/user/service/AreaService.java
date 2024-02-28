package com.spud.nimbus.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spud.nimbus.api.user.vo.AreaVO;
import com.spud.nimbus.user.model.Area;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 省市区地区信息 服务类
 * </p>
 *
 * @author spud
 * @since 2024-01-24
 */
public interface AreaService extends IService<Area> {

	/**
	 * 获取地址列表
	 * @return
	 */
	public List<AreaVO> areaList();

	/**
	 * 根据省市区地区信息id获取省市区地区信息
	 * @param areaId 省市区地区信息id
	 * @return 省市区地区信息
	 */
	AreaVO getByAreaId(Long areaId);

	/**
	 * 保存省市区地区信息
	 * @param area 省市区地区信息
	 * @return
	 */
	boolean save(Area area);

	/**
	 * 更新省市区地区信息
	 * @param area 省市区地区信息
	 */
	void update(Area area);

	/**
	 * 根据省市区地区信息id删除省市区地区信息
	 * @param areaId
	 */
	void deleteById(Long areaId);

	/**
	 * 根据上级地址id，获取地址列表
	 * @param pid
	 * @return
	 */
	List<AreaVO> listByPid(Long pid);

	/**
	 * 获取省市区三级结构完整的集合
	 * @return 省市区三级结构完整的集合
	 */
	List<AreaVO> getAreaListInfo();

	/**
	 * 获取可用的省市区列表
	 * @return
	 */
	List<AreaVO> listAreaOfEnable();

}
