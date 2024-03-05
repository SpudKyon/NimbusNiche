package com.spud.nimbus.search.feign;

import com.spud.nimbus.api.search.dto.EsPageDTO;
import com.spud.nimbus.api.search.feign.SearchOrderFeignClient;
import com.spud.nimbus.api.search.vo.EsPageVO;
import com.spud.nimbus.api.search.vo.search.EsOrderVO;
import com.spud.nimbus.common.dto.OrderSearchDTO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.search.manager.OrderSearchManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author spud
 * @date 2024/2/26
 */
@RestController
public class SearchOrderFeignController implements SearchOrderFeignClient {

	private final OrderSearchManager orderSearchManager;

	@Autowired
	public SearchOrderFeignController(OrderSearchManager orderSearchManager) {
		this.orderSearchManager = orderSearchManager;
	}

	@Override
	public Result<EsPageVO<EsOrderVO>> getOrderPage(OrderSearchDTO orderSearch) {
		EsPageDTO pageDTO = new EsPageDTO();
		pageDTO.setPageNum(orderSearch.getPageNum());
		pageDTO.setPageSize(orderSearch.getPageSize());
		return Result.success(orderSearchManager.pageSearchResult(pageDTO, orderSearch));
	}

}
