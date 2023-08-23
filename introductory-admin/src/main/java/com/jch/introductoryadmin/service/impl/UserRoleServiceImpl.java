package com.jch.introductoryadmin.service.impl;

import com.jch.introductoryadmin.domain.UserRole;
import com.jch.introductoryadmin.dao.UserRoleDao;
import com.jch.introductoryadmin.service.IUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

/**
 * (UserRole)表服务实现类
 *
 * @author 吉晨浩
 * @since 2023-08-22 20:57:38
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements IUserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserRole queryById(Integer id) {
        return this.userRoleDao.queryById(id);
    }
    
    /**
     * 分页查询
     *
     * @param userRole 筛选条件
     * @param pageNo 起始页
     * @param pageSize 每页大小
     * @return 查询结果
     */
    @Override
    public Page<UserRole> queryByPage(UserRole userRole, int pageNo, int pageSize) {
        long total = this.userRoleDao.count(userRole);
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return new PageImpl<>(this.userRoleDao.queryAllByLimit(userRole, pageable), pageable, total);
    }

    /**
     * 新增数据
     *
     * @param userRole 实例对象
     * @return 实例对象
     */
    @Override
    public UserRole insert(UserRole userRole) {
        this.userRoleDao.insert(userRole);
        return userRole;
    }

    /**
     * 修改数据
     *
     * @param userRole 实例对象
     * @return 实例对象
     */
    @Override
    public UserRole update(UserRole userRole) {
        this.userRoleDao.update(userRole);
        return this.queryById(userRole.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userRoleDao.deleteById(id) > 0;
    }
}
