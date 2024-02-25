package com.spud.nimbus.platform.controller;

import com.spud.nimbus.api.auth.vo.AuthAccountVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.security.AuthUserContext;
import com.spud.nimbus.platform.dto.ChangeAccountDTO;
import com.spud.nimbus.platform.service.SysUserAccountService;
import com.spud.nimbus.platform.service.SysUserService;
import com.spud.nimbus.platform.vo.SysUserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/25
 */
@RequestMapping(value = "/sys_user/account")
@RestController
@Tag(name = "平台用户账号信息")
public class SysUserAccountController {

  private final SysUserAccountService SysUserAccountService;

  private final SysUserService SysUserService;

  public SysUserAccountController(SysUserAccountService sysUserAccountService, SysUserService sysUserService) {
    this.SysUserAccountService = sysUserAccountService;
    this.SysUserService = sysUserService;
  }


  @GetMapping
  @Operation(summary = "获取账号信息", description = "获取账号信息")
  public Result<AuthAccountVO> getAccount(Long userId) {
    return SysUserAccountService.getByUserIdAndSysType(userId, AuthUserContext.get().getSysType());
  }


  @PostMapping
  @Operation(summary = "添加账号", description = "添加账号")
  public Result<Void> addAccount(@Valid @RequestBody ChangeAccountDTO changeAccountDTO) {
    SysUserVO sysUserVO = SysUserService.getByUserId(changeAccountDTO.getUserId());
    if (sysUserVO == null) {
      return Result.showFailMsg("无法获取账户信息");
    }
    if (Objects.equals(sysUserVO.getHasAccount(), 1)) {
      return Result.showFailMsg("已有账号，无需重复添加");
    }
    return SysUserAccountService.save(changeAccountDTO);
  }

  @PutMapping
  @Operation(summary = "修改账号", description = "修改账号")
  public Result<Void> updateAccount(@Valid @RequestBody ChangeAccountDTO changeAccountDTO) {
    SysUserVO sysUserVO = SysUserService.getByUserId(changeAccountDTO.getUserId());
    if (sysUserVO == null || Objects.equals(sysUserVO.getHasAccount(), 0)) {
      return Result.showFailMsg("无法获取账户信息");
    }
    return SysUserAccountService.update(changeAccountDTO);
  }
}
