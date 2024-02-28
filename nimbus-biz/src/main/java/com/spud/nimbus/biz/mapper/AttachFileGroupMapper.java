package com.spud.nimbus.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spud.nimbus.biz.model.AttachFileGroup;
import com.spud.nimbus.biz.vo.AttachFileGroupVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Mapper
public interface AttachFileGroupMapper extends BaseMapper<AttachFileGroup> {

	/**
	 * 获取列表
	 * @param shopId
	 * @return 列表
	 */
	List<AttachFileGroupVO> list(@Param("shopId") Long shopId);

	/**
	 * 根据id获取
	 * @param attachFileGroupId id
	 * @return
	 */
	AttachFileGroupVO getByAttachFileGroupId(@Param("attachFileGroupId") Long attachFileGroupId);

	/**
	 * 保存
	 * @param attachFileGroup
	 */
	void save(@Param("attachFileGroup") AttachFileGroup attachFileGroup);

	/**
	 * 更新
	 * @param attachFileGroup
	 */
	void update(@Param("attachFileGroup") AttachFileGroup attachFileGroup);

	/**
	 * 根据id删除
	 * @param attachFileGroupId
	 */
	void deleteById(@Param("attachFileGroupId") Long attachFileGroupId);

}