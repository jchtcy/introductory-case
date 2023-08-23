package com.jch.introductoryadmin.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;

/**
 * (UserRole)实体类
 *
 * @author 吉晨浩
 * @since 2023-08-20 15:33:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    
    private Integer userId;
    
    private Integer roleId;


}

