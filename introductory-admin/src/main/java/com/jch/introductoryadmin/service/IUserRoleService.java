package com.jch.introductoryadmin.service;

import com.jch.introductoryadmin.domain.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (UserRole)表服务接口
 *
 * @author 吉晨浩
 * @since 2023-08-22 19:58:50
 */
public interface IUserRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserRole queryById(Integer id);
    
   /**
     * 分页查询
     *
     * @param userRole 筛选条件
     * @param pageNo 起始页
     * @param pageSize 每页大小              
     * @return 查询结果
     */
    Page<UserRole> queryByPage(UserRole userRole, int pageNo, int pageSize);

    /**
     * 新增数据
     *
     * @param userRole 实例对象
     * @return 实例对象
     */
    UserRole insert(UserRole userRole);

    /**
     * 修改数据
     *
     * @param userRole 实例对象
     * @return 实例对象
     */
    UserRole update(UserRole userRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
