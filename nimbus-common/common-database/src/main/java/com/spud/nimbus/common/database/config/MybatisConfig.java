package com.spud.nimbus.common.database.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author spud
 * @date 2024/1/25
 */
@Configuration
@MapperScan({ "com.spud.nimbus.**.mapper" })
public class MybatisConfig {

}
