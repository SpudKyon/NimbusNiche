package com.spud.nimbus.nimbus.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author spud
 * @date 2024/2/26
 */
@SpringBootApplication(scanBasePackages = { "com.spud.nimbus" })
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
