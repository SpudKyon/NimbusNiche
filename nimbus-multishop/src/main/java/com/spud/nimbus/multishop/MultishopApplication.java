package com.spud.nimbus.multishop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author spud
 * @date 2024/2/23
 */
@SpringBootApplication(scanBasePackages = { "com.spud.nimbus" })
@EnableFeignClients(basePackages = { "com.spud.nimbus.api.**.feign" })
public class MultishopApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultishopApplication.class, args);
	}

}
