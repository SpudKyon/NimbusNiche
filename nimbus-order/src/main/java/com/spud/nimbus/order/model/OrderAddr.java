package com.spud.nimbus.order.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户订单配送地址
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("order_addr")
public class OrderAddr implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId(value = "order_addr_id", type = IdType.AUTO)
	private Long orderAddrId;

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
	 * 用户ID
	 */
	@TableField("user_id")
	private Long userId;

	/**
	 * 收货人
	 */
	@TableField("consignee")
	private String consignee;

	/**
	 * 省ID
	 */
	@TableField("province_id")
	private Long provinceId;

	/**
	 * 省
	 */
	@TableField("province")
	private String province;

	/**
	 * 城市ID
	 */
	@TableField("city_id")
	private Long cityId;

	/**
	 * 城市
	 */
	@TableField("city")
	private String city;

	/**
	 * 区域ID
	 */
	@TableField("area_id")
	private Long areaId;

	/**
	 * 区
	 */
	@TableField("area")
	private String area;

	/**
	 * 地址
	 */
	@TableField("addr")
	private String addr;

	/**
	 * 邮编
	 */
	@TableField("post_code")
	private String postCode;

	/**
	 * 手机
	 */
	@TableField("mobile")
	private String mobile;

	/**
	 * 经度
	 */
	@TableField("lng")
	private BigDecimal lng;

	/**
	 * 纬度
	 */
	@TableField("lat")
	private BigDecimal lat;

}
