package com.spud.nimbus.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.api.user.vo.AreaVO;
import com.spud.nimbus.common.exception.NimbusException;
import com.spud.nimbus.user.mapper.AreaMapper;
import com.spud.nimbus.user.model.Area;
import com.spud.nimbus.user.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 省市区地区信息 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-24
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {

	private final AreaMapper areaMapper;

	@Autowired
	public AreaServiceImpl(AreaMapper areaMapper) {
		this.areaMapper = areaMapper;
	}

	@Override
	public List<AreaVO> areaList() {
		return areaMapper.list();
	}

	@Override
	public AreaVO getByAreaId(Long areaId) {
		return areaMapper.getByAreaId(areaId);
	}

	@Override
	public boolean save(Area area) {
		return super.save(area);
	}

	@Override
	public void update(Area area) {
		areaMapper.update(area);
	}

	@Override
	public void deleteById(Long areaId) {
		int areaNum = areaMapper.countByAreaId(areaId);
		if (areaNum > 0) {
			throw new NimbusException("请先删除子地区");
		}
		areaMapper.deleteById(areaId);
	}

	@Override
	public List<AreaVO> listByPid(Long pid) {
		return areaMapper.listByPid(pid);
	}

	@Override
	public List<AreaVO> getAreaListInfo() {
		List<AreaVO> areaVOList = areaMapper.getAreaListInfo();
		for (AreaVO province : areaVOList) {
			List<Long> cities = new ArrayList<>();
			for (AreaVO city : province.getAreas()) {
				cities.add(city.getAreaId());
				List<Long> areas = new ArrayList<>();
				for (AreaVO area : city.getAreas()) {
					areas.add(area.getAreaId());
				}
				city.setAreaIds(areas);
			}
			province.setAreaIds(cities);
		}
		return areaVOList;
	}

	@Override
	public List<AreaVO> listAreaOfEnable() {
		return areaMapper.listAreaOfEnable();
	}

}
