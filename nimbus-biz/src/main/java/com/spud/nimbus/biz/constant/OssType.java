package com.spud.nimbus.biz.constant;

/**
 * @author spud
 * @date 2024/2/18
 */
public enum OssType {

  /**
   * 阿里云oss
   */
  ALI(0),

  /**
   * minio
   */
  MINIO(1),
  ;

  private final Integer value;

  OssType(Integer value) {
    this.value = value;
  }

  public Integer value() {
    return value;
  }

}