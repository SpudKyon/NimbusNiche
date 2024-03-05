package com.spud.nimbus.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.api.auth.feign.AccountFeignClient;
import com.spud.nimbus.api.rbac.dto.UserRoleDTO;
import com.spud.nimbus.api.rbac.feign.UserRoleFeignClient;
import com.spud.nimbus.common.cache.constant.CacheNames;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.util.PageUtil;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.platform.mapper.SysUserMapper;
import com.spud.nimbus.platform.model.SysUser;
import com.spud.nimbus.platform.service.SysUserService;
import com.spud.nimbus.platform.vo.SysUserSimpleVO;
import com.spud.nimbus.platform.vo.SysUserVO;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 平台用户 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	@Resource
	private SysUserMapper sysUserMapper;

	private final AccountFeignClient accountFeignClient;

	private final UserRoleFeignClient userRoleFeignClient;

	@Autowired
	public SysUserServiceImpl(AccountFeignClient accountFeignClient, UserRoleFeignClient userRoleFeignClient) {
		this.accountFeignClient = accountFeignClient;
		this.userRoleFeignClient = userRoleFeignClient;
	}

	@Override
	@Cacheable(cacheNames = CacheNames.PLATFORM_SIMPLE_INFO_KEY, key = "#userId")
	public SysUserSimpleVO getSimpleByUserId(Long userId) {
		return sysUserMapper.getSimpleByUserId(userId);
	}

	@Override
	public PageVO<SysUserVO> pageByShopId(PageDTO pageDTO, Long shopId, String nickName) {
		return PageUtil.doPage(pageDTO, () -> sysUserMapper.listByShopId(shopId, nickName));
	}

	@Override
	public SysUserVO getByUserId(Long userId) {
		SysUserVO sysUser = sysUserMapper.getByUserId(userId);
		Result<List<Long>> roleIds = userRoleFeignClient.getRoleIds(sysUser.getSysUserId());
		sysUser.setRoleIds(roleIds.getData());
		return sysUser;
	}

	@Override
	@GlobalTransactional(rollbackFor = Exception.class)
	@Transactional(rollbackFor = Exception.class)
	public void save(SysUser sysUser, List<Long> roleIds) {
		UserRoleDTO userRoleDTO = new UserRoleDTO();
		userRoleDTO.setRoleIds(roleIds);
		sysUserMapper.save(sysUser);
		userRoleDTO.setUserId(sysUser.getSysUserId());
		userRoleFeignClient.saveByUserIdAndSysType(userRoleDTO);
	}

	@Override
	@GlobalTransactional(rollbackFor = Exception.class)
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(cacheNames = CacheNames.PLATFORM_SIMPLE_INFO_KEY, key = "#sysUser.sysUserId")
	public void update(SysUser sysUser, List<Long> roleIds) {
		UserRoleDTO userRoleDTO = new UserRoleDTO();
		userRoleDTO.setRoleIds(roleIds);
		userRoleDTO.setUserId(sysUser.getSysUserId());
		sysUserMapper.update(sysUser);
		userRoleFeignClient.updateByUserIdAndSysType(userRoleDTO);
	}

	@Override
	@GlobalTransactional(rollbackFor = Exception.class)
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(cacheNames = CacheNames.PLATFORM_SIMPLE_INFO_KEY, key = "#sysUserId")
	public void deleteById(Long sysUserId) {
		accountFeignClient.deleteByUserIdAndSysType(sysUserId);
		userRoleFeignClient.deleteByUserIdAndSysType(sysUserId);
		sysUserMapper.deleteById(sysUserId);
	}

}
