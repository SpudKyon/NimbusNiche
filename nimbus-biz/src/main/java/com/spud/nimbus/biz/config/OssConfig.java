package com.spud.nimbus.biz.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author spud
 * @date 2024/2/18
 */
@Data
@ToString
@RefreshScope
@Configuration
public class OssConfig {

  @Value("${biz.oss.endpoint}")
  private String endpoint;

  @Value("${biz.oss.bucket}")
  private String bucket;
  @Value("${biz.oss.access-key-id}")

  private String accessKeyId;
  @Value("${biz.oss.access-key-secret}")
  private String accessKeySecret;

  @Value("${biz.oss.type}")
  private Integer ossType;

  /**
   * 最大上传长度单位m，默认20M
   */
  @Value("${biz.oss.maxLength:20}")
  private Integer maxLength;
}
