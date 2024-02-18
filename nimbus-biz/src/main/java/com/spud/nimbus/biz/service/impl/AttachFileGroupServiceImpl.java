package com.spud.nimbus.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.biz.mapper.AttachFileGroupMapper;
import com.spud.nimbus.biz.mapper.AttachFileMapper;
import com.spud.nimbus.biz.model.AttachFileGroup;
import com.spud.nimbus.biz.service.AttachFileGroupService;
import com.spud.nimbus.biz.vo.AttachFileGroupVO;
import com.spud.nimbus.common.security.AuthUserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/18
 */
@Service
public class AttachFileGroupServiceImpl extends ServiceImpl<AttachFileGroupMapper, AttachFileGroup> implements AttachFileGroupService {

  @Autowired
  private AttachFileMapper attachFileMapper;

  @Autowired
  private AttachFileGroupMapper attachFileGroupMapper;

  @Override
  public List<AttachFileGroupVO> volist() {
    return attachFileGroupMapper.list(AuthUserContext.get().getTenantId());
  }

  @Override
  public AttachFileGroupVO getByAttachFileGroupId(Long attachFileGroupId) {
    return attachFileGroupMapper.getByAttachFileGroupId(attachFileGroupId);
  }

  @Override
  public boolean save(AttachFileGroup attachFileGroup) {
    attachFileGroup.setShopId(AuthUserContext.get().getTenantId());
    return super.save(attachFileGroup);
  }

  @Override
  public void update(AttachFileGroup attachFileGroup) {
    attachFileGroupMapper.update(attachFileGroup);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void deleteById(Long attachFileGroupId) {
    attachFileGroupMapper.deleteById(attachFileGroupId);
    attachFileMapper.updateBatchByAttachFileGroupId(attachFileGroupId);
  }
}
