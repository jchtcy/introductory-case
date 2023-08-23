package com.jch.introductoryadmin.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;

/**
 * (Role)实体类
 *
 * @author 吉晨浩
 * @since 2023-08-20 15:33:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer roleId;
    
    private String roleName;
    
    private String roleDesc;


}

