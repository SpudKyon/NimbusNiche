package com.spud.nimbus.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spud.nimbus.biz.model.AttachFile;
import com.spud.nimbus.biz.vo.AttachFileVO;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.vo.PageVO;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/18
 */
public interface AttachFileService extends IService<AttachFile> {

	/**
	 * 分页获取上传文件记录表列表
	 * @param pageDTO 分页参数
	 * @param fileName 文件名
	 * @param fileGroupId 文件分组id
	 * @return 上传文件记录表列表分页数据
	 */
	PageVO<AttachFileVO> page(PageDTO pageDTO, String fileName, Long fileGroupId);

	/**
	 * 保存上传文件记录表
	 * @param attachFiles 上传文件记录表
	 */
	void save(List<AttachFile> attachFiles);

	/**
	 * 更新上传文件记录表
	 * @param attachFile 上传文件记录表
	 */
	void update(AttachFile attachFile);

	/**
	 * 根据上传文件记录表id删除上传文件记录表
	 * @param fileId
	 */
	void deleteById(Long fileId);

	/**
	 * 更新文件名称
	 * @param attachFile
	 * @return
	 */
	Boolean updateFileName(AttachFile attachFile);

}
