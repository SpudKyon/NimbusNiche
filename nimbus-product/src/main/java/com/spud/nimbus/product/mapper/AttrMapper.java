package com.spud.nimbus.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spud.nimbus.api.product.vo.AttrVO;
import com.spud.nimbus.common.database.util.PageAdapter;
import com.spud.nimbus.product.dto.AttrDTO;
import org.apache.ibatis.annotations.Mapper;
import com.spud.nimbus.product.model.Attr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 属性信息 Mapper 接口
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Mapper
public interface AttrMapper extends BaseMapper<Attr> {
  /**
   * 获取属性信息列表
   *
   * @param pageAdapter 分页参数
   * @param attrDTO     属性数据
   * @return 属性信息列表
   */
  List<AttrVO> list(@Param("page") PageAdapter pageAdapter, @Param("attr") AttrDTO attrDTO);

  /**
   * 获取属性总数
   *
   * @param attrDTO
   * @return
   */
  Long countAttr(@Param("attr") AttrDTO attrDTO);

  /**
   * 根据属性信息id获取属性信息
   *
   * @param attrId 属性信息id
   * @return 属性信息
   */
  AttrVO getByAttrId(@Param("attrId") Long attrId);

  /**
   * 保存属性信息
   *
   * @param attr 属性信息
   */
  void save(@Param("attr") Attr attr);

  /**
   * 更新属性信息
   *
   * @param attr 属性信息
   */
  void update(@Param("attr") Attr attr);

  /**
   * 根据属性信息id删除属性信息
   *
   * @param attrId
   */
  void deleteById(@Param("attrId") Long attrId);

  /**
   * 根据分类和属性类型，获取对应的属性列表
   *
   * @param categoryId
   * @return
   */
  List<AttrVO> getAttrsByCategoryIdAndAttrType(@Param("categoryId") Long categoryId);

  /**
   * 获取店铺中的销售属性
   *
   * @param shopId
   * @return 销售属性列表
   */
  List<AttrVO> getShopAttrs(Long shopId);

}
