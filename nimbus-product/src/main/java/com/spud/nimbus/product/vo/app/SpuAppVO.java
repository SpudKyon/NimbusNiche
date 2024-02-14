package com.spud.nimbus.product.vo.app;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spud.nimbus.api.product.vo.SpuAttrValueVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author spud
 * @date 2024/2/10
 */
@Data
@ToString
public class SpuAppVO {
  private static final long serialVersionUID = 1L;

  @Schema(description = "spu id" )
  private Long spuId;

  @Schema(description = "店铺id" )
  private Long shopId;

  @Schema(description = "spu名称" )
  private String name;

  @Schema(description = "卖点" )
  private String sellingPoint;

//  @JsonSerialize(using = ImgJsonSerializer.class)
  @Schema(description = "商品介绍主图" )
  private String mainImgUrl;

//  @JsonSerialize(using = ImgJsonSerializer.class)
  @Schema(description = "商品介绍主图 多个图片逗号分隔" )
  private String imgUrls;

  @Schema(description = "售价，整数方式保存" )
  private Long priceFee;

  @Schema(description = "市场价，整数方式保存" )
  private Long marketPriceFee;

  @Schema(description = "商品详情" )
  private String detail;

  @Schema(description = "总库存" )
  private Integer totalStock;

  @Schema(description = "规格属性" )
  private List<SpuAttrValueVO> spuAttrValues;

  @Schema(description = "sku列表" )
  private List<SkuAppVO> skus;

  @Schema(description = "商品销量" )
  private Integer saleNum;
}
