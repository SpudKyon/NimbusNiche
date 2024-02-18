package com.spud.nimbus.biz.config;

import com.spud.nimbus.common.exception.NimbusException;
import com.spud.nimbus.common.response.ResultCode;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author spud
 * @date 2024/2/18
 */
@Slf4j
@Component
public class MinioTemplate implements InitializingBean {

  @Autowired
  private OssConfig ossConfig;
  private MinioClient minioClient;

  @Override
  public void afterPropertiesSet() throws Exception {
    this.minioClient = MinioClient.builder().endpoint(ossConfig.getEndpoint())
            .credentials(ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret())
            .build();
  }


  /**
   * 删除文件
   *
   * @param objectName 文件名称
   * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#removeObject
   */
  public void removeObject(String objectName) throws Exception {
    minioClient.removeObject(RemoveObjectArgs.builder().object(objectName).bucket(ossConfig.getBucket()).build());
  }

  /**
   * 获得上传的URL
   *
   * @param objectName
   */
  public String getPresignedObjectUrl(String objectName) {
    try {
      return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(ossConfig.getBucket()).object(objectName).expiry(10, TimeUnit.MINUTES).method(Method.PUT).build());
    } catch (Exception e) {
      log.error("minio获取上传URL错误：", e);
      throw new NimbusException(ResultCode.EXCEPTION);
    }
  }

  public void uploadMinio(byte[] bytes, String filePath, String contentType) throws IOException {
    InputStream input = null;
    try {
      input = new ByteArrayInputStream(bytes);
      minioClient.putObject(
              PutObjectArgs.builder()
                      .bucket(ossConfig.getBucket())
                      .contentType(contentType)
                      .stream(input, input.available(), -1)
                      .object(filePath)
                      .build()
      );
    } catch (Exception e) {
      log.error("minio上传文件错误：", e);
    } finally {
      if (Objects.nonNull(input)) {
        input.close();
      }
    }
  }
}