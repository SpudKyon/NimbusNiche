package com.spud.nimbus.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spud.nimbus.platform.model.SysUser;
import com.spud.nimbus.platform.vo.SysUserSimpleVO;
import com.spud.nimbus.platform.vo.SysUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 平台用户 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

	/**
	 * 根据用户id获取当前登陆的商家用户信息
	 * @param userId 用户id
	 * @return 商家用户信息
	 */
	SysUserSimpleVO getSimpleByUserId(@Param("userId") Long userId);

	/**
	 * 获取平台用户列表
	 * @param shopId 平台id
	 * @param nickName 昵称
	 * @return 平台用户列表
	 */
	List<SysUserVO> listByShopId(@Param("shopId") Long shopId, @Param("nickName") String nickName);

	/**
	 * 根据用户id获取商家用户信息
	 * @param userId 用户id
	 * @return 商家用户信息
	 */
	SysUserVO getByUserId(@Param("userId") Long userId);

	/**
	 * 保存商家用户信息
	 * @param sysUser
	 */
	void save(@Param("sysUser") SysUser sysUser);

	/**
	 * 更新平台用户信息
	 * @param sysUser
	 */
	void update(@Param("sysUser") SysUser sysUser);

	/**
	 * 根据平台用户id删除平台用户
	 * @param sysUserId
	 */
	void deleteById(@Param("sysUserId") Long sysUserId);

}
