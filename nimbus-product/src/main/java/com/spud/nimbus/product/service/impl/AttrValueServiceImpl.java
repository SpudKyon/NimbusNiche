package com.spud.nimbus.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.api.product.vo.AttrVO;
import com.spud.nimbus.product.mapper.AttrValueMapper;
import com.spud.nimbus.product.model.Attr;
import com.spud.nimbus.product.model.AttrValue;
import com.spud.nimbus.product.service.AttrValueService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 属性值信息 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class AttrValueServiceImpl extends ServiceImpl<AttrValueMapper, AttrValue> implements AttrValueService {

	@Override
	public void saveByAttrValuesAndAttrId(List<AttrValue> attrValues, Long attrId) {

	}

	@Override
	public void update(Attr attrVO, AttrVO dbAttr) {

	}

}
