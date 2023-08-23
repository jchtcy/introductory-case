package com.jch.introductoryadmin.service.impl;

import com.jch.introductoryadmin.domain.Menu;
import com.jch.introductoryadmin.dao.MenuDao;
import com.jch.introductoryadmin.service.IMenuService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

/**
 * (Menu)表服务实现类
 *
 * @author 吉晨浩
 * @since 2023-08-22 20:57:38
 */
@Service("menuService")
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuDao menuDao;

    /**
     * 通过ID查询单条数据
     *
     * @param menuId 主键
     * @return 实例对象
     */
    @Override
    public Menu queryById(Integer menuId) {
        return this.menuDao.queryById(menuId);
    }
    
    /**
     * 分页查询
     *
     * @param menu 筛选条件
     * @param pageNo 起始页
     * @param pageSize 每页大小
     * @return 查询结果
     */
    @Override
    public Page<Menu> queryByPage(Menu menu, int pageNo, int pageSize) {
        long total = this.menuDao.count(menu);
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return new PageImpl<>(this.menuDao.queryAllByLimit(menu, pageable), pageable, total);
    }

    /**
     * 新增数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    @Override
    public Menu insert(Menu menu) {
        this.menuDao.insert(menu);
        return menu;
    }

    /**
     * 修改数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    @Override
    public Menu update(Menu menu) {
        this.menuDao.update(menu);
        return this.queryById(menu.getMenuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param menuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer menuId) {
        return this.menuDao.deleteById(menuId) > 0;
    }
}
