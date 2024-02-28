package com.spud.nimbus.product.bo;

import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/10
 */
@Data
@ToString
public class SkuWithStockBO {

	private Long id;

	private Long skuId;

	private Long spuId;

	private Integer count;

}
