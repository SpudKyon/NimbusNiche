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
 * 热搜
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("hot_search")
public class HotSearch implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "hot_search_id", type = IdType.AUTO)
	private Long hotSearchId;

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
	 * 店铺ID 0为全平台热搜
	 */
	@TableField("shop_id")
	private Long shopId;

	/**
	 * 内容
	 */
	@TableField("content")
	private String content;

	/**
	 * 顺序
	 */
	@TableField("seq")
	private Integer seq;

	/**
	 * 状态 0下线 1上线
	 */
	@TableField("status")
	private Integer status;

	/**
	 * 热搜标题
	 */
	@TableField("title")
	private String title;

}
