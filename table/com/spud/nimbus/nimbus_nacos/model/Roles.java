package com.spud.nimbus.nimbus_nacos.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author spud
 * @since 2024-01-23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("roles")
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "username", type = IdType.AUTO)
    private String username;

    @TableId(value = "role", type = IdType.AUTO)
    private String role;


}
