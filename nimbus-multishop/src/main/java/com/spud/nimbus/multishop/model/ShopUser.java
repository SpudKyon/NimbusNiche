package com.spud.nimbus.multishop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 商家用户
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("shop_user")
public class ShopUser implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 商家用户id
	 */
	@TableId(value = "shop_user_id", type = IdType.AUTO)
	private Long shopUserId;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private LocalDateTime createTime;

	/**
	 * 更新时间
	 */
	@TableField("update_time")
	private LocalDateTime updateTime;

	/**
	 * 关联店铺id
	 */
	@TableField("shop_id")
	private Long shopId;

	/**
	 * 昵称
	 */
	@TableField("nick_name")
	private String nickName;

	/**
	 * 员工编号
	 */
	@TableField("code")
	private String code;

	/**
	 * 联系方式
	 */
	@TableField("phone_num")
	private String phoneNum;

	/**
	 * 是否已经设置账号 0:未设置 1:已设置
	 */
	@TableField("has_account")
	private Integer hasAccount;

}
