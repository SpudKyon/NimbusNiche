package com.spud.nimbus.leaf.feign;

import com.spud.nimbus.api.leaf.feign.SegmentFeignClient;
import com.spud.nimbus.leaf.common.Status;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.leaf.exception.LeafServerException;
import com.spud.nimbus.leaf.exception.NoKeyException;
import com.spud.nimbus.leaf.service.SegmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/24
 */
@Slf4j
@RestController
public class SegmentFeignController implements SegmentFeignClient {


  @Autowired
  private SegmentService segmentService;

  @Override
  public Result<Long> getSegmentId(String key) {
    return Result.success(get(key, segmentService.getId(key)));
  }


  private Long get(String key, com.spud.nimbus.leaf.common.Result id) {
    com.spud.nimbus.leaf.common.Result result;
    if (key == null || key.isEmpty()) {
      throw new NoKeyException();
    }
    result = id;
    if (Objects.equals(result.getStatus(), Status.EXCEPTION)) {
      throw new LeafServerException(result.toString());
    }
    return result.getId();
  }
}
