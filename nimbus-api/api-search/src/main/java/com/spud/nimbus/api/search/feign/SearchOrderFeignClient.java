package com.spud.nimbus.api.search.feign;

import com.spud.nimbus.api.search.vo.EsPageVO;
import com.spud.nimbus.api.search.vo.search.EsOrderVO;
import com.spud.nimbus.common.dto.OrderSearchDTO;
import com.spud.nimbus.common.feign.FeignInsideAuthConfig;
import com.spud.nimbus.common.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author spud
 * @date 2024/2/10
 */
@FeignClient(value = "nimbus-search", contextId = "searchOrder")
public interface SearchOrderFeignClient {

	/**
	 * 订单搜索
	 * @param orderSearch 订单搜索参数
	 * @return 订单列表
	 */
	@PutMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/searchOrder/getOrderPage")
	Result<EsPageVO<EsOrderVO>> getOrderPage(@RequestBody OrderSearchDTO orderSearch);

}