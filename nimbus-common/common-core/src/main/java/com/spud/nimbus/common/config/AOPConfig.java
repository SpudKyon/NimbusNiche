package com.spud.nimbus.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AOP配置类
 * 用于解决AOP切面无法获取代理对象问题
 * 详见：<a href="https://docs.spring.io/spring/docs/5.1.3.RELEASE/spring-framework-reference/core.html#aop-understanding-aop-proxies">...</a>
 * @author spud
 * @date 2024/1/28
 */
@EnableAspectJAutoProxy(exposeProxy = true)
@Configuration
public class AOPConfig {
}
