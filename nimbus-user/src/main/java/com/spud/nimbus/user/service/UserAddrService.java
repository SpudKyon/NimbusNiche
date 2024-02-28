package com.spud.nimbus.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spud.nimbus.common.order.vo.UserAddrVO;
import com.spud.nimbus.user.model.UserAddr;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户地址 服务类
 * </p>
 *
 * @author spud
 * @since 2024-01-24
 */
@Service
public interface UserAddrService extends IService<UserAddr> {

	/**
	 * 获取用户地址列表
	 * @param userId
	 * @return
	 */
	List<UserAddrVO> userAddrList(Long userId);

	/**
	 * 保存用户地址
	 * @param userAddr 用户地址
	 * @return
	 */
	boolean save(UserAddr userAddr);

	/**
	 * 更新用户地址
	 * @param userAddr 用户地址
	 */
	void update(UserAddr userAddr);

	/**
	 * 删除地址
	 * @param addrId 地址id
	 * @param userId 用户id
	 */
	void deleteUserAddrByUserId(Long addrId, Long userId);

	/**
	 * 根据用户地址id和用户id获取用户地址信息
	 * @param addrId 地址id
	 * @param userId 用户id
	 * @return 用户地址信息
	 */
	UserAddrVO getUserAddrByUserId(Long addrId, Long userId);

	/**
	 * 用户地址的数量
	 * @param userId 用户id
	 * @return 数量
	 */
	int countByUserId(Long userId);

}
