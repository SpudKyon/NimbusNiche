package com.spud.nimbus.api.product.bo;

import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/9
 */
@Data
@ToString
public class EsAttrBO {

	/**
	 * 规格id
	 */
	private Long attrId;

	/**
	 * 规格名
	 */
	private String attrName;

	/**
	 * 规格值id
	 */
	private Long attrValueId;

	/**
	 * 规格值名称
	 */
	private String attrValueName;

}
