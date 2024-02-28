package com.spud.nimbus.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.spud.nimbus.common.model.BaseModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author spud
 * @since 2024-01-24
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user")
public class User extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String DISTRIBUTED_ID_KEY = "nimbus-user";

	/**
	 * ID
	 */
	@TableId(value = "user_id", type = IdType.AUTO)
	private Long userId;

	/**
	 * 用户昵称
	 */
	@TableField("nick_name")
	private String nickName;

	/**
	 * 头像图片路径
	 */
	@TableField("pic")
	private String pic;

	/**
	 * 状态 1 正常 0 无效
	 */
	@TableField("status")
	private Integer status;

}
