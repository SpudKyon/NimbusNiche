package com.spud.nimbus.rbac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author spud
 * @date 2024/2/22
 */
@SpringBootApplication(scanBasePackages = { "com.spud.nimbus" })
@EnableFeignClients(basePackages = { "com.spud.nimbus.api.**.feign" })
public class RbacApplication {

	public static void main(String[] args) {
		SpringApplication.run(RbacApplication.class, args);
	}

}
