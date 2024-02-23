package com.spud.nimbus.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spud.nimbus.common.cache.constant.CacheNames;
import com.spud.nimbus.rbac.mapper.MenuMapper;
import com.spud.nimbus.rbac.service.MenuService;
import com.spud.nimbus.rbac.model.Menu;
import com.spud.nimbus.rbac.vo.MenuSimpleVO;
import com.spud.nimbus.rbac.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

  @Autowired
  private MenuMapper menuMapper;

  @Override
  public MenuVO getByMenuId(Long menuId) {
    return menuMapper.getByMenuId(menuId);
  }

  @Override
  @CacheEvict(cacheNames = CacheNames.MENU_LIST_KEY, key = "#menu.bizType")
  public boolean save(Menu menu) {
    return super.save(menu);
  }

  @Override
  @CacheEvict(cacheNames = CacheNames.MENU_LIST_KEY, key = "#menu.bizType")
  public void update(Menu menu) {
    menuMapper.update(menu);
  }

  @Override
  @CacheEvict(cacheNames = CacheNames.MENU_LIST_KEY, key = "#sysType")
  public void deleteById(Long menuId, Integer sysType) {
    menuMapper.deleteById(menuId, sysType);
  }

  @Override
  @Cacheable(cacheNames = CacheNames.MENU_LIST_KEY, key = "#sysType")
  public List<Menu> listBySysType(Integer sysType) {
    return menuMapper.listBySysType(sysType);
  }

  @Override
  public List<MenuSimpleVO> listWithPermissions(Integer sysType) {
    return menuMapper.listWithPermissions(sysType);
  }

  @Override
  @Cacheable(cacheNames = CacheNames.MENU_ID_LIST_KEY, key = "#userId")
  public List<Long> listMenuIds(Long userId) {
    return menuMapper.listMenuIds(userId);
  }

}
