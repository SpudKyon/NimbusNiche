package com.spud.nimbus.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author spud
 * @date 2024/2/26
 */
@SpringBootApplication(scanBasePackages = { "com.spud.nimbus" })
@EnableFeignClients(basePackages = { "com.spud.nimbus.api.**.feign" })
public class SearchApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(SearchApplication.class, args);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
