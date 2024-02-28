package com.spud.nimbus.common.cache.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/1/30
 */
@AllArgsConstructor
@Data
@ToString
public class CacheNameWithTtlBO {

	private String cacheName;

	private Integer ttl;

}
