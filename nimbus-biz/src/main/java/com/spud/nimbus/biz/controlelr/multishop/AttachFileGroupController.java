package com.spud.nimbus.biz.controlelr.multishop;

import com.spud.nimbus.biz.dto.AttachFileGroupDTO;
import com.spud.nimbus.biz.model.AttachFileGroup;
import com.spud.nimbus.biz.service.AttachFileGroupService;
import com.spud.nimbus.biz.vo.AttachFileGroupVO;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.util.BeanUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/18
 */
@RestController("multishopAttachFileGroupController")
@RequestMapping("/m/attach_file_group")
@Tag(name = "店铺-文件分组")
public class AttachFileGroupController {

	@Autowired
	private AttachFileGroupService attachFileGroupService;

	@GetMapping("/list")
	@Operation(summary = "获取列表", description = "分页获取列表")
	public Result<List<AttachFileGroupVO>> list() {
		List<AttachFileGroupVO> attachFileGroupPage = attachFileGroupService.volist();
		return Result.success(attachFileGroupPage);
	}

	@GetMapping
	@Operation(summary = "获取", description = "根据attachFileGroupId获取")
	public Result<AttachFileGroupVO> getByAttachFileGroupId(@RequestParam Long attachFileGroupId) {
		return Result.success(attachFileGroupService.getByAttachFileGroupId(attachFileGroupId));
	}

	@PostMapping
	@Operation(summary = "保存", description = "保存")
	public Result<Void> save(@Valid @RequestBody AttachFileGroupDTO attachFileGroupDTO) {
		AttachFileGroup attachFileGroup = BeanUtil.map(attachFileGroupDTO, AttachFileGroup.class);
		attachFileGroup.setAttachFileGroupId(null);
		attachFileGroupService.save(attachFileGroup);
		return Result.success(null);
	}

	@PutMapping
	@Operation(summary = "更新", description = "更新")
	public Result<Void> update(@Valid @RequestBody AttachFileGroupDTO attachFileGroupDTO) {
		AttachFileGroup attachFileGroup = BeanUtil.map(attachFileGroupDTO, AttachFileGroup.class);
		attachFileGroupService.update(attachFileGroup);
		return Result.success(null);
	}

	@DeleteMapping
	@Operation(summary = "删除", description = "根据id删除")
	public Result<Void> delete(@RequestParam Long attachFileGroupId) {
		attachFileGroupService.deleteById(attachFileGroupId);
		return Result.success(null);
	}

}
