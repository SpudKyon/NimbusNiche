package com.spud.nimbus.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spud.nimbus.biz.model.AttachFile;
import com.spud.nimbus.biz.vo.AttachFileVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 上传文件记录表 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Mapper
public interface AttachFileMapper extends BaseMapper<AttachFile> {

  /**
   * 获取上传文件记录表列表
   *
   * @param fileName    文件名称
   * @param shopId
   * @param fileGroupId 文件分组id
   * @return 文件记录表列表
   */
  List<AttachFileVO> list(@Param("fileName") String fileName, @Param("shopId") Long shopId, @Param("fileGroupId") Long fileGroupId);

  /**
   * 保存上传文件记录表
   *
   * @param attachFiles 上传文件记录表
   * @param shopId      店铺id
   */
  void save(@Param("attachFiles") List<AttachFile> attachFiles, @Param("shopId") Long shopId);

  /**
   * 更新上传文件记录表
   *
   * @param attachFile
   * @param attachFile 上传文件记录表
   */
  void update(@Param("attachFile") AttachFile attachFile);

  /**
   * 根据上传文件记录表id删除上传文件记录表
   *
   * @param fileId
   */
  void deleteById(@Param("fileId") Long fileId);

  /**
   * 根据id获取文件信息
   *
   * @param fileId
   * @return
   */
  AttachFile getById(@Param("fileId") Long fileId);

  /**
   * 批量更新文件的分组
   *
   * @param attachFileGroupId
   */
  void updateBatchByAttachFileGroupId(@Param("attachFileGroupId") Long attachFileGroupId);
}