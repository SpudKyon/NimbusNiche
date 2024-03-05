package com.spud.nimbus.search.listener;

import cn.throwx.canal.gule.model.CanalBinLogEvent;
import cn.throwx.canal.gule.model.CanalBinLogResult;
import cn.throwx.canal.gule.support.processor.BaseCanalBinlogEventProcessor;
import cn.throwx.canal.gule.support.processor.ExceptionHandler;
import com.spud.nimbus.api.product.bo.EsProductBO;
import com.spud.nimbus.api.product.feign.ProductFeignClient;
import com.spud.nimbus.common.exception.NimbusException;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.util.Json;
import com.spud.nimbus.search.bo.SpuBO;
import com.spud.nimbus.search.constant.EsIndexEnum;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author spud
 * @date 2024/2/26
 */
@Slf4j
@Component
public class SpuCanalListener extends BaseCanalBinlogEventProcessor<SpuBO> {

	private final ProductFeignClient productFeignClient;

	private final RestHighLevelClient restHighLevelClient;

	@Autowired
	public SpuCanalListener(ProductFeignClient productFeignClient, RestHighLevelClient restHighLevelClient) {
		this.productFeignClient = productFeignClient;
		this.restHighLevelClient = restHighLevelClient;
	}

	/**
	 * 插入商品，此时插入es
	 */
	@Override
	protected void processInsertInternal(CanalBinLogResult<SpuBO> result) {
		Long spuId = result.getPrimaryKey();
		Result<EsProductBO> esProductBO = productFeignClient.loadEsProductBO(spuId);
		if (!esProductBO.isSuccess()) {
			throw new NimbusException("创建索引异常");
		}

		IndexRequest request = new IndexRequest(EsIndexEnum.PRODUCT.value());
		request.id(String.valueOf(spuId));
		request.source(Json.toJsonString(esProductBO.getData()), XContentType.JSON);
		try {
			IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
			log.info(indexResponse.toString());

		}
		catch (IOException e) {
			log.error(e.toString());
			throw new NimbusException("保存es信息异常", e);
		}
	}

	/**
	 * 更新商品，删除商品索引，再重新构建一个
	 */
	@Override
	protected void processUpdateInternal(CanalBinLogResult<SpuBO> result) {
		Long spuId = result.getPrimaryKey();
		Result<EsProductBO> esProductBO = productFeignClient.loadEsProductBO(spuId);
		String source = Json.toJsonString(esProductBO.getData());
		UpdateRequest request = new UpdateRequest(EsIndexEnum.PRODUCT.value(), String.valueOf(spuId));
		request.doc(source, XContentType.JSON);
		request.docAsUpsert(true);
		try {
			UpdateResponse updateResponse = restHighLevelClient.update(request, RequestOptions.DEFAULT);
			log.info(updateResponse.toString());
		}
		catch (IOException e) {
			log.error(e.toString());
			throw new NimbusException("删除es信息异常", e);
		}
	}

	@Override
	protected ExceptionHandler exceptionHandler() {
		return (CanalBinLogEvent event, Throwable throwable) -> {
			throw new NimbusException("创建索引异常", throwable);
		};
	}

}
