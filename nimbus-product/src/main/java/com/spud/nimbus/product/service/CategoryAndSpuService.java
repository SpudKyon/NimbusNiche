package com.spud.nimbus.product.service;

import com.spud.nimbus.product.dto.CategoryDTO;

/**
 * @author spud
 * @date 2024/2/10
 */
public interface CategoryAndSpuService {

	/**
	 * 分类的启用和禁用
	 * @param categoryDTO
	 * @return
	 */
	Boolean categoryEnableOrDisable(CategoryDTO categoryDTO);

}
