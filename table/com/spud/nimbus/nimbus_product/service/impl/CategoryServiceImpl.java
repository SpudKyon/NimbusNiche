package com.spud.nimbus.nimbus_product.service.impl;

import com.spud.nimbus.nimbus_product.model.Category;
import com.spud.nimbus.nimbus_product.dao.CategoryMapper;
import com.spud.nimbus.nimbus_product.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分类信息 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
