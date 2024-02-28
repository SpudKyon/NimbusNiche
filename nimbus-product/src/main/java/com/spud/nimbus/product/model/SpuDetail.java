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
 * 商品详情信息
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Data
@ToString
@Accessors(chain = true)
@TableName("spu_detail")
public class SpuDetail extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 商品id
	 */
	@TableId(value = "spu_id", type = IdType.AUTO)
	private Long spuId;

	/**
	 * 商品详情
	 */
	@TableField("detail")
	private String detail;

}
