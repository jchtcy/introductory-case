package com.jch.introductoryadmin.service;

import com.jch.introductoryadmin.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Role)表服务接口
 *
 * @author 吉晨浩
 * @since 2023-08-22 19:58:50
 */
public interface IRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    Role queryById(Integer roleId);
    
   /**
     * 分页查询
     *
     * @param role 筛选条件
     * @param pageNo 起始页
     * @param pageSize 每页大小              
     * @return 查询结果
     */
    Page<Role> queryByPage(Role role, int pageNo, int pageSize);

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role insert(Role role);

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Integer update(Role role);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer roleId);
    /**
     * 新增数据
     *
     * @param role 实体
     * @return 新增结果
     */
    Integer addRole(Role role);
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    Role getRoleById(Integer id);
}
