package com.jch.introductoryadmin.service.impl;

import com.jch.introductoryadmin.domain.RoleMenu;
import com.jch.introductoryadmin.dao.RoleMenuDao;
import com.jch.introductoryadmin.service.IRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

/**
 * (RoleMenu)表服务实现类
 *
 * @author 吉晨浩
 * @since 2023-08-22 20:57:38
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl implements IRoleMenuService {
    @Autowired
    private RoleMenuDao roleMenuDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public RoleMenu queryById(Integer id) {
        return this.roleMenuDao.queryById(id);
    }
    
    /**
     * 分页查询
     *
     * @param roleMenu 筛选条件
     * @param pageNo 起始页
     * @param pageSize 每页大小
     * @return 查询结果
     */
    @Override
    public Page<RoleMenu> queryByPage(RoleMenu roleMenu, int pageNo, int pageSize) {
        long total = this.roleMenuDao.count(roleMenu);
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return new PageImpl<>(this.roleMenuDao.queryAllByLimit(roleMenu, pageable), pageable, total);
    }

    /**
     * 新增数据
     *
     * @param roleMenu 实例对象
     * @return 实例对象
     */
    @Override
    public RoleMenu insert(RoleMenu roleMenu) {
        this.roleMenuDao.insert(roleMenu);
        return roleMenu;
    }

    /**
     * 修改数据
     *
     * @param roleMenu 实例对象
     * @return 实例对象
     */
    @Override
    public RoleMenu update(RoleMenu roleMenu) {
        this.roleMenuDao.update(roleMenu);
        return this.queryById(roleMenu.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.roleMenuDao.deleteById(id) > 0;
    }
}
