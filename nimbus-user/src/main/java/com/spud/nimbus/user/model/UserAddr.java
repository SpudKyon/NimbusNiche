package com.spud.nimbus.user.model;

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
 * 用户地址
 * </p>
 *
 * @author spud
 * @since 2024-01-24
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user_addr")
public class UserAddr implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "addr_id", type = IdType.AUTO)
    private Long addrId;

    /**
     * 建立时间
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
     * 手机
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 是否默认地址 1是
     */
    @TableField("is_default")
    private Integer isDefault;

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
     * 区ID
     */
    @TableField("area_id")
    private Long areaId;

    /**
     * 区
     */
    @TableField("area")
    private String area;

    /**
     * 邮编
     */
    @TableField("post_code")
    private String postCode;

    /**
     * 地址
     */
    @TableField("addr")
    private String addr;

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
