package com.spud.nimbus.api.search.vo;

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
public class EsPageVO<T> {

  @Schema(description = "总页数" )
  private Integer pages;

  @Schema(description = "总条目数" )
  private Long total;

  @Schema(description = "结果集" )
  private List<T> list;
}
