package com.spud.nimbus.user.controller;

import com.spud.nimbus.api.user.vo.AreaVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.user.service.AreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/1
 */
@RestController("AreaController")
@RequestMapping("/v1/area")
@Tag(name = "地区信息")
public class AreaController {

  @Autowired
  private AreaService areaService;

  @GetMapping("/list")
  @Operation(summary = "获取省市区地区信息列表" , description = "获取省市区地区信息列表")
  public Result<List<AreaVO>> list() {
    List<AreaVO> list = areaService.getAreaListInfo();
    return Result.success(list);
  }

  @GetMapping("/list_by_pid")
  @Operation(summary = "通过父级id获取区域列表" , description = "通过父级id获取区域列表")
  public Result<List<AreaVO>> listByPid(Long pid) {
    List<AreaVO> list = areaService.listByPid(pid);
    return Result.success(list);
  }
}
