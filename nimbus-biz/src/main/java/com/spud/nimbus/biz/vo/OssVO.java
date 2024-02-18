package com.spud.nimbus.biz.vo;

import com.spud.nimbus.common.vo.BaseVO;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/18
 */
@Data
@ToString
public class OssVO extends BaseVO {

  private String accessid;

  private String policy;

  private String signature;

  private String dir;

  private String host;

  private Integer expire;

  private String fileName;

  private String actionUrl;

  /**
   * url列表--minio中一条链接对应一个上传的文件
   * @return
   */
  private List<OssVO> ossList;
}
