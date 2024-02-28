package com.spud.nimbus.leaf;

import com.spud.nimbus.leaf.common.Result;

/**
 * @author spud
 * @date 2024/2/24
 */
public interface IDGen {

	/**
	 * get
	 * @param key key
	 * @return Result
	 */
	Result get(String key);

	/**
	 * init
	 * @return inited
	 */
	boolean init();

}
