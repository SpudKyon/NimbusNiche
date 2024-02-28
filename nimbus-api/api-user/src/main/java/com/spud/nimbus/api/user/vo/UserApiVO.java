package com.spud.nimbus.api.user.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spud.nimbus.common.serializer.ImgJsonSerializer;
import com.spud.nimbus.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 用户表VO
 *
 * @author spud
 * @date 2024/1/30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class UserApiVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	@Schema(description = "ID")
	private Long userId;

	@Schema(description = "用户昵称")
	private String nickName;

	@Schema(description = "头像图片路径")
	@JsonSerialize(using = ImgJsonSerializer.class)
	private String pic;

	@Schema(description = "状态 1 正常 0 无效")
	private Integer status;

	/**
	 * openId list
	 */
	@Schema(description = "是否创建过店铺")
	private List<String> bizUserIdList;

}
