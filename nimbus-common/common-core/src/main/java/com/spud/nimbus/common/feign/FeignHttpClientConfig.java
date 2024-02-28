package com.spud.nimbus.common.feign;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign httpclient配置
 *
 * @author spud
 * @date 2024/1/25
 */
@Configuration
public class FeignHttpClientConfig {

	@Bean(destroyMethod = "close")
	public CloseableHttpClient httpClient() {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(400); // 连接池最大连接数
		connectionManager.setDefaultMaxPerRoute(100); // 每个主机的并发

		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(2000) // 从连接池中获取连接的超时时间
				.setConnectTimeout(2000) // 连接超时时间
				.setSocketTimeout(15000) // 数据传输的超时时
				.build();
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().setConnectionManager(connectionManager)
				.setDefaultRequestConfig(requestConfig).evictExpiredConnections();
		return httpClientBuilder.build();
	}

}
