package com.jch.introductoryadmin.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;

/**
 * (Menu)实体类
 *
 * @author 吉晨浩
 * @since 2023-08-20 15:33:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer menuId;
    
    private String component;
    
    private String path;
    
    private String redirect;
    
    private String name;
    
    private String title;
    
    private String icon;
    
    private Integer parentId;
    
    private String isLeaf;
    
    private Integer hidden;


}

