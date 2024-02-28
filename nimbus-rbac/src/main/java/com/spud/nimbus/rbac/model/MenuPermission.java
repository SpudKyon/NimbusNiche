package com.spud.nimbus.rbac.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单资源
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("menu_permission")
@ToString
public class MenuPermission implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单资源用户id
	 */
	@TableId(value = "menu_permission_id", type = IdType.AUTO)
	private Long menuPermissionId;

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
	 * 资源关联菜单
	 */
	@TableField("menu_id")
	private Long menuId;

	/**
	 * 业务类型 1 店铺菜单 2平台菜单
	 */
	@TableField("biz_type")
	private Integer bizType;

	/**
	 * 权限对应的编码
	 */
	@TableField("permission")
	private String permission;

	/**
	 * 资源名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 资源对应服务器路径
	 */
	@TableField("uri")
	private String uri;

	/**
	 * 请求方法 1.GET 2.POST 3.PUT 4.DELETE
	 */
	@TableField("method")
	private Integer method;

}
