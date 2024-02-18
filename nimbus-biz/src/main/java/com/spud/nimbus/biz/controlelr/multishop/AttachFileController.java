package com.spud.nimbus.biz.controlelr.multishop;

import com.spud.nimbus.biz.dto.AttachFileDTO;
import com.spud.nimbus.biz.model.AttachFile;
import com.spud.nimbus.biz.service.AttachFileService;
import com.spud.nimbus.biz.vo.AttachFileVO;
import com.spud.nimbus.common.database.dto.PageDTO;
import com.spud.nimbus.common.database.vo.PageVO;
import com.spud.nimbus.common.exception.NimbusException;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.util.BeanUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author spud
 * @date 2024/2/18
 */
@RestController("multishopAttachFileController")
@RequestMapping("/m/attach_file")
@Tag(name = "上传文件记录表")
public class AttachFileController {

  @Autowired
  private AttachFileService attachFileService;

  @GetMapping("/page")
  @Operation(summary = "获取上传文件记录表列表", description = "分页获取上传文件记录表列表")
  public Result<PageVO<AttachFileVO>> page(@Valid PageDTO pageDTO, String fileName, Long fileGroupId) {
    if (fileGroupId == 0) {
      fileGroupId = null;
    }
    PageVO<AttachFileVO> attachFilePage = attachFileService.page(pageDTO, fileName, fileGroupId);
    return Result.success(attachFilePage);
  }

  @PostMapping
  @Operation(summary = "保存上传文件记录", description = "保存上传文件记录")
  public Result<Void> save(@RequestBody List<AttachFileDTO> attachFileDtos) {
    List<AttachFile> attachFiles = BeanUtil.mapAsList(attachFileDtos, AttachFile.class);
    attachFileService.save(attachFiles);
    return Result.success(null);
  }

  /**
   * 更改文件名或分组
   */
  @PutMapping("/update_file")
  @Operation(summary = "更新文件记录", description = "更新文件记录")
  public Result<Boolean> updateFileName(@RequestBody AttachFileDTO attachFileDto) {
    if (Objects.isNull(attachFileDto.getFileName())) {
      // 图片名称不能为空
      throw new NimbusException("图片名称不能为空");
    }
    AttachFile attachFile = BeanUtil.map(attachFileDto, AttachFile.class);
    return Result.success(attachFileService.updateFileName(attachFile));
  }

  @DeleteMapping
  @Operation(summary = "删除上传文件记录", description = "根据上传文件记录表id删除上传文件记录")
  public Result<Void> delete(@RequestParam Long fileId) {
    attachFileService.deleteById(fileId);
    return Result.success(null);
  }
}
