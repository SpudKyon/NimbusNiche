package com.spud.nimbus.common.database.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @author spud
 * @date 2024/1/28
 */
public class PageVO<T> {

  @Schema(description = "总页数")
  private Integer pages;

  @Schema(description = "总条目数")
  private Long total;

  @Schema(description = "结果集")
  private List<T> list;

  public Integer getPages() {
    return pages;
  }

  public void setPages(Integer pages) {
    this.pages = pages;
  }

  public Long getTotal() {
    return total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }

  @Override
  public String toString() {
    return "PageVO{" +
            ", pages=" + pages +
            ", total=" + total +
            ", list=" + list +
            '}';
  }
}