package com.spud.nimbus.biz.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.spud.nimbus.common.model.BaseModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 上传文件记录表
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Data
@ToString
@Accessors(chain = true)
@TableName("attach_file")
public class AttachFile extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "file_id", type = IdType.AUTO)
	private Long fileId;

	/**
	 * 文件路径
	 */
	@TableField("file_path")
	private String filePath;

	/**
	 * 文件类型
	 */
	@TableField("file_type")
	private String fileType;

	/**
	 * 文件名
	 */
	@TableField("file_name")
	private String fileName;

	/**
	 * 文件大小
	 */
	@TableField("file_size")
	private Integer fileSize;

	/**
	 * 店铺id
	 */
	@TableField("shop_id")
	private Long shopId;

	/**
	 * 文件 1:图片 2:视频 3:文件
	 */
	@TableField("type")
	private Integer type;

	/**
	 * 文件分组id
	 */
	@TableField("attach_file_group_id")
	private Long attachFileGroupId;

}
