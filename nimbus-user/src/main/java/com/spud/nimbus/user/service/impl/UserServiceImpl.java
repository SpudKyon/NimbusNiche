package com.spud.nimbus.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.api.auth.constant.SysTypeEnum;
import com.spud.nimbus.api.auth.dto.AuthAccountDTO;
import com.spud.nimbus.api.auth.feign.AccountFeignClient;
import com.spud.nimbus.api.auth.vo.AuthAccountVO;
import com.spud.nimbus.api.leaf.feign.SegmentFeignClient;
import com.spud.nimbus.api.user.vo.UserApiVO;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.util.PageUtil;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.common.exception.NimbusException;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.response.ResultCode;
import com.spud.nimbus.common.util.IpHelper;
import com.spud.nimbus.user.dto.UserRegisterDTO;
import com.spud.nimbus.user.mapper.UserMapper;
import com.spud.nimbus.user.model.User;
import com.spud.nimbus.user.service.UserService;
import com.spud.nimbus.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private AccountFeignClient accountFeignClient;

	@Autowired
	private SegmentFeignClient segmentFeignClient;

	@Override
	public PageVO<UserApiVO> page(PageDTO pageDTO) {
		return PageUtil.doPage(pageDTO, () -> userMapper.list());
	}

	@Override
	public UserApiVO getByUserId(Long userId) {
		return userMapper.getByUserId(userId);
	}

	@Override
	public void update(User user) {
		userMapper.update(user);
	}

	@Override
	public List<UserApiVO> getUserByUserIds(List<Long> userIds) {
		if (CollUtil.isEmpty(userIds)) {
			return new ArrayList<>();
		}
		return userMapper.getUserByUserIds(userIds);
	}

	@Override
	public Long save(UserRegisterDTO param) {
		this.checkRegisterInfo(param);

		Result<Long> segmentIdResponse = segmentFeignClient.getSegmentId(User.DISTRIBUTED_ID_KEY);
		if (!segmentIdResponse.isSuccess()) {
			throw new NimbusException(ResultCode.EXCEPTION);
		}
		Long userId = segmentIdResponse.getData();

		param.setUserId(userId);

		AuthAccountDTO authAccountDTO = new AuthAccountDTO();
		authAccountDTO.setCreateIp(IpHelper.getIpAddr());
		authAccountDTO.setPassword(param.getPassword());
		authAccountDTO.setIsAdmin(0);
		authAccountDTO.setSysType(SysTypeEnum.ORDINARY.value());
		authAccountDTO.setUsername(param.getUserName());
		authAccountDTO.setStatus(1);
		authAccountDTO.setUserId(userId);

		// 保存统一账户信息
		Result<Long> serverResponse = accountFeignClient.save(authAccountDTO);
		// 抛异常回滚
		if (!serverResponse.isSuccess()) {
			throw new NimbusException(serverResponse.getMsg());
		}

		User user = new User();
		user.setUserId(userId);
		user.setPic(param.getImg());
		user.setNickName(param.getNickName());
		user.setStatus(1);
		// 这里保存之后才有用户id
		userMapper.save(user);

		return serverResponse.getData();
	}

	private void checkRegisterInfo(UserRegisterDTO userRegisterDTO) {
		Result<AuthAccountVO> responseEntity = accountFeignClient.getByUsernameAndSysType(userRegisterDTO.getUserName(),
				SysTypeEnum.ORDINARY);
		if (!responseEntity.isSuccess()) {
			throw new NimbusException(responseEntity.getMsg());
		}
		if (Objects.nonNull(responseEntity.getData())) {
			throw new NimbusException("用户名已存在");
		}
	}

}
