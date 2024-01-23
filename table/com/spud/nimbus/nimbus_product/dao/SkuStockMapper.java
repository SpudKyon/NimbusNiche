package com.spud.nimbus.nimbus_product.dao;

import com.spud.nimbus.nimbus_product.model.SkuStock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 库存信息 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Mapper
public interface SkuStockMapper extends BaseMapper<SkuStock> {

}
