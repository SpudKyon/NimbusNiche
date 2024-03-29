package com.spud.nimbus.api.product.vo;

import com.spud.nimbus.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/9
 */
@Data
@ToString
public class CategoryVO extends BaseVO {

	private static final long serialVersionUID = 1L;

	@Schema(description = "分类id")
	private Long categoryId;

	@Schema(description = "店铺id")
	private Long shopId;

	@Schema(description = "父ID")
	private Long parentId;

	@Schema(description = "分类名称")
	private String name;

	@Schema(description = "分类描述")
	private String desc;

	@Schema(description = "分类地址{parent_id}-{child_id},...")
	private String path;

	@Schema(description = "状态 1:enable, 0:disable, -1:deleted")
	private Integer status;

	// @JsonSerialize(using = ImgJsonSerializer.class)
	@Schema(description = "分类图标")
	private String icon;

	// @JsonSerialize(using = ImgJsonSerializer.class)
	@Schema(description = "分类的显示图片")
	private String imgUrl;

	@Schema(description = "分类层级 从0开始")
	private Integer level;

	@Schema(description = "排序")
	private Integer seq;

	@Schema(description = "上级分类名称")
	private List<String> pathNames;

	@Schema(description = "子分类列表")
	private List<CategoryVO> categories;

}
