package com.spud.nimbus.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spud.nimbus.biz.model.AttachFileGroup;
import com.spud.nimbus.biz.vo.AttachFileGroupVO;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/18
 */
public interface AttachFileGroupService extends IService<AttachFileGroup> {

  /**
   * 获取列表
   *
   * @return 列表数据
   */
  List<AttachFileGroupVO> volist();

  /**
   * 根据id获取
   *
   * @param attachFileGroupId id
   * @return
   */
  AttachFileGroupVO getByAttachFileGroupId(Long attachFileGroupId);

  /**
   * 保存
   *
   * @param attachFileGroup
   * @return
   */
  boolean save(AttachFileGroup attachFileGroup);

  /**
   * 更新
   *
   * @param attachFileGroup
   */
  void update(AttachFileGroup attachFileGroup);

  /**
   * 根据id删除
   *
   * @param attachFileGroupId
   */
  void deleteById(Long attachFileGroupId);
}
