package com.jch.introductoryadmin.service;

import com.jch.introductoryadmin.domain.RoleMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (RoleMenu)表服务接口
 *
 * @author 吉晨浩
 * @since 2023-08-22 19:58:50
 */
public interface IRoleMenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RoleMenu queryById(Integer id);
    
   /**
     * 分页查询
     *
     * @param roleMenu 筛选条件
     * @param pageNo 起始页
     * @param pageSize 每页大小              
     * @return 查询结果
     */
    Page<RoleMenu> queryByPage(RoleMenu roleMenu, int pageNo, int pageSize);

    /**
     * 新增数据
     *
     * @param roleMenu 实例对象
     * @return 实例对象
     */
    RoleMenu insert(RoleMenu roleMenu);

    /**
     * 修改数据
     *
     * @param roleMenu 实例对象
     * @return 实例对象
     */
    RoleMenu update(RoleMenu roleMenu);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
