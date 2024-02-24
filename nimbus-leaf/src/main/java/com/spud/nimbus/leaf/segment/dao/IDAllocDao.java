package com.spud.nimbus.leaf.segment.dao;

import com.spud.nimbus.leaf.segment.model.LeafAlloc;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/24
 */
public interface IDAllocDao {

	List<LeafAlloc> getAllLeafAllocs();

	/**
	 * updateMaxIdAndGetLeafAlloc
	 * @param tag tag
	 * @return LeafAlloc
	 */
	LeafAlloc updateMaxIdAndGetLeafAlloc(String tag);

	/**
	 * updateMaxIdByCustomStepAndGetLeafAlloc
	 * @param leafAlloc leafAlloc
	 * @return LeafAlloc
	 */
	LeafAlloc updateMaxIdByCustomStepAndGetLeafAlloc(LeafAlloc leafAlloc);

	/**
	 * getAllTags
	 * @return List<String>
	 */
	List<String> getAllTags();

}
