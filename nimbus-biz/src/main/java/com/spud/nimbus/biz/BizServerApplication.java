package com.spud.nimbus.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author spud
 * @date 2024/2/18
 */
@EnableFeignClients(basePackages = { "com.spud.nimbus.api.**.feign" })
@SpringBootApplication(scanBasePackages = { "com.spud.nimbus" })
public class BizServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BizServerApplication.class, args);
	}

}
