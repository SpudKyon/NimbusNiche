package com.spud.nimbus.product.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.spud.nimbus.common.model.BaseModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * spu信息
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Data
@ToString
@Accessors(chain = true)
@TableName("spu")
public class Spu extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * spu id
     */
    @TableId(value = "spu_id", type = IdType.AUTO)
    private Long spuId;

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
     * 品牌ID
     */
    @TableField("brand_id")
    private Long brandId;

    /**
     * 分类ID
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 店铺分类ID
     */
    @TableField("shop_category_id")
    private Long shopCategoryId;

    /**
     * 店铺id
     */
    @TableField("shop_id")
    private Long shopId;

    /**
     * 商品名称
     */
    @TableField("name")
    private String name;

    /**
     * 卖点
     */
    @TableField("selling_point")
    private String sellingPoint;

    /**
     * 商品介绍主图
     */
    @TableField("main_img_url")
    private String mainImgUrl;

    /**
     * 商品图片 多个图片逗号分隔
     */
    @TableField("img_urls")
    private String imgUrls;

    /**
     * 商品视频
     */
    @TableField("video")
    private String video;

    /**
     * 售价，整数方式保存
     */
    @TableField("price_fee")
    private Long priceFee;

    /**
     * 市场价，整数方式保存
     */
    @TableField("market_price_fee")
    private Long marketPriceFee;

    /**
     * 状态 -1:删除, 0:下架, 1:上架
     */
    @TableField("status")
    private Integer status;

    /**
     * sku是否含有图片 0无 1有
     */
    @TableField("has_sku_img")
    private Integer hasSkuImg;

    /**
     * 序号
     */
    @TableField("seq")
    private Integer seq;


}
