package com.jch.introductoryadmin.service;

import com.jch.introductoryadmin.domain.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Menu)表服务接口
 *
 * @author 吉晨浩
 * @since 2023-08-22 19:58:50
 */
public interface IMenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    Menu queryById(Integer menuId);
    
   /**
     * 分页查询
     *
     * @param menu 筛选条件
     * @param pageNo 起始页
     * @param pageSize 每页大小              
     * @return 查询结果
     */
    Page<Menu> queryByPage(Menu menu, int pageNo, int pageSize);

    /**
     * 新增数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    Menu insert(Menu menu);

    /**
     * 修改数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    Menu update(Menu menu);

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer menuId);

}
