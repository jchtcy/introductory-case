package com.jch.introductoryadmin.service.impl;

import com.jch.introductoryadmin.domain.Menu;
import com.jch.introductoryadmin.dao.MenuDao;
import com.jch.introductoryadmin.service.IMenuService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
     * 查询
     *
     * @param menu 筛选条件
     * @return 查询结果
     */
    @Override
    public List<Menu> queryList(Menu menu) {

        return this.menuDao.queryList(menu);
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

    /**
     * 查询所有菜单
     *
     * @return 查询结果
     */
    @Override
    public List<Menu> getAllMenu() {
        Menu father = new Menu();
        father.setParentId(0);
        List<Menu> fatherList = this.menuDao.queryList(father);
        setMenuChildren(fatherList);
        return fatherList;
    }

    private void setMenuChildren(List<Menu> fatherList) {
        if (fatherList != null ) {
            for (Menu menu : fatherList) {
                Menu son = new Menu();
                son.setParentId(menu.getMenuId());
                List<Menu> sonList = this.menuDao.queryList(son);
                menu.setChildren(sonList);
                //递归
                setMenuChildren(sonList);
            }
        }
    }

    /**
     * 根据用户id查询菜单权限
     * @param userId
     * @return
     */
    @Override
    public List<Menu> getListByUserId(Integer userId) {
        //父菜单
        List<Menu> menuList = this.menuDao.getListByUserId(userId, 0);
        //子菜单
        setMenuChildrenByUserId(userId, menuList);
        return menuList;
    }

    private void setMenuChildrenByUserId(Integer userId, List<Menu> menuList) {
        if (menuList != null) {
            for (Menu menu : menuList) {
                List<Menu> subMenuList = this.menuDao.getListByUserId(userId, menu.getMenuId());
                menu.setChildren(subMenuList);
                setMenuChildrenByUserId(userId, subMenuList);
            }
        }
    }
}
