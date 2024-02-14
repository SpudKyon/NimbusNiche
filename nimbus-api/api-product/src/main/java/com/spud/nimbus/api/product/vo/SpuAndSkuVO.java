package com.spud.nimbus.api.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/9
 */
@Data
@ToString
public class SpuAndSkuVO {

    @Schema(description = "spu信息" )
    private SpuVO spu;

    @Schema(description = "sku信息" )
    private SkuVO sku;
}
