package com.spud.nimbus.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author spud
 * @date 2024/2/10
 */
@SpringBootApplication(scanBasePackages = {"com.spud.nimbus"})
@EnableFeignClients(basePackages = {"com.spud.nimbus.api.**.feign"})
public class ProductApplication {
  public static void main(String[] args) {
    SpringApplication.run(ProductApplication.class, args);
  }
}
