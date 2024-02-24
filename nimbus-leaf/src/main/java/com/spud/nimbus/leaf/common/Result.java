package com.spud.nimbus.leaf.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author spud
 * @date 2024/2/24
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Result {

  private long id;

  private Status status;
}
