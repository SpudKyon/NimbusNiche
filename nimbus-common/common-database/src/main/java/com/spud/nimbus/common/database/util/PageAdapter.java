package com.spud.nimbus.common.database.util;

import com.spud.nimbus.common.database.dto.PageDTO;
import lombok.Data;
import lombok.ToString;

import static cn.hutool.core.util.PageUtil.getStart;

/**
 * @author spud
 * @date 2024/1/28
 */
@Data
@ToString
public class PageAdapter {

  private int begin;

  private int size;

  public PageAdapter(PageDTO page) {
    this.begin = getStart(page.getPageNum() - 1, page.getPageSize());
    this.size = page.getPageSize();
  }
}
