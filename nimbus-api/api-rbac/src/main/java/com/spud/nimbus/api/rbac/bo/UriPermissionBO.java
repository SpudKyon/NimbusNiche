package com.spud.nimbus.api.rbac.bo;

import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/9
 */
@Data
@ToString
public class UriPermissionBO {

  /**
   * 请求方法 1.GET 2.POST 3.PUT 4.DELETE
   */
  private Integer method;

  /**
   * uri
   */
  private String uri;

  /**
   * permission
   */
  private String permission;
}
