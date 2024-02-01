package com.spud.nimbus.user.controller;

import com.spud.nimbus.api.user.vo.UserApiVO;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.util.BeanUtil;
import com.spud.nimbus.user.dto.UserDTO;
import com.spud.nimbus.user.model.User;
import com.spud.nimbus.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author spud
 * @date 2024/2/1
 */
@RestController("shopUserController")
@RequestMapping("/v1/user/shop")
@Tag(name = "店铺-用户表")
public class ShopUserController {

  @Autowired
  private UserService userService;

  @GetMapping("/page")
  @Operation(summary = "获取用户表列表", description = "分页获取用户表列表")
  public Result<PageVO<UserApiVO>> page(@Valid PageDTO pageDTO) {
    PageVO<UserApiVO> userPage = userService.page(pageDTO);
    return Result.success(userPage);
  }

  @GetMapping
  @Operation(summary = "获取用户表", description = "根据userId获取用户表")
  public Result<UserApiVO> getByUserId(@RequestParam Long userId) {
    UserApiVO userVO = BeanUtil.map(userService.getByUserId(userId), UserApiVO.class);
    return Result.success(userVO);
  }


  @PutMapping
  @Operation(summary = "更新用户表", description = "更新用户表")
  public Result<Void> update(@Valid @RequestBody UserDTO userDTO) {
    User user = BeanUtil.map(userDTO, User.class);
    userService.update(user);
    return Result.success(null);
  }

}