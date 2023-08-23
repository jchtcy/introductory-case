package com.jch.introductoryadmin.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;

/**
 * (RoleMenu)实体类
 *
 * @author 吉晨浩
 * @since 2023-08-20 15:33:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    
    private Integer roleId;
    
    private Integer menuId;


}

