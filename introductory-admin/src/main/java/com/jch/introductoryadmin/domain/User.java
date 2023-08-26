package com.jch.introductoryadmin.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.util.List;

/**
 * (User)实体类
 *
 * @author 吉晨浩
 * @since 2023-08-20 15:33:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    
    private String username;
    
    private String password;
    
    private String email;
    
    private String phone;
    
    private Integer status;
    
    private String avatar;
    
    private Integer deleted;

    private List<Integer> roleIdList;
}

