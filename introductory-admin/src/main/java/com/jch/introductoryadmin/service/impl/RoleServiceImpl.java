package com.jch.introductoryadmin.service.impl;

import com.jch.introductoryadmin.dao.RoleMenuDao;
import com.jch.introductoryadmin.domain.Role;
import com.jch.introductoryadmin.dao.RoleDao;
import com.jch.introductoryadmin.domain.RoleMenu;
import com.jch.introductoryadmin.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (Role)表服务实现类
 *
 * @author 吉晨浩
 * @since 2023-08-22 20:57:38
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleMenuDao roleMenuDao;
    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public Role queryById(Integer roleId) {
        return this.roleDao.queryById(roleId);
    }
    
    /**
     * 分页查询
     *
     * @param role 筛选条件
     * @param pageNo 起始页
     * @param pageSize 每页大小
     * @return 查询结果
     */
    @Override
    public Page<Role> queryByPage(Role role, int pageNo, int pageSize) {
        long total = this.roleDao.count(role);
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<Role> roles = this.roleDao.queryAllByLimit(role, pageable);
        if(roles != null) {
            for (Role r : roles) {
                List<Integer> menuIdList = this.roleMenuDao.getMenuIdListByRoleId(r.getRoleId());
                r.setMenuIdList(menuIdList);
            }
        }
        return new PageImpl<>(roles, pageable, total);
    }

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role insert(Role role) {
        this.roleDao.insert(role);
        return role;
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Integer update(Role role) {
        this.roleDao.update(role);
        Integer roleId = role.getRoleId();
        this.roleMenuDao.deleteByroleId(roleId);
        List<Integer> menuIdList = role.getMenuIdList();
        if (menuIdList != null) {
            return this.roleMenuDao.insertBatchMenuIdList(roleId, menuIdList);
        }
        return 0;
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer roleId) {
        return this.roleMenuDao.deleteByroleId(roleId) > 0 && this.roleDao.deleteById(roleId) > 0;
    }

    @Override
    @Transactional
    public Integer addRole(Role role) {
        this.roleDao.insert(role);
        List<Integer> menuIdList = role.getMenuIdList();
        if (menuIdList != null) {
            return this.roleMenuDao.insertBatchMenuIdList(role.getRoleId(), menuIdList);
        }
        return 0;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Override
    public Role getRoleById(Integer id) {
        Role role = this.roleDao.queryById(id);
        List<Integer> menuIdList = this.roleMenuDao.getMenuIdListByRoleId(id);
        role.setMenuIdList(menuIdList);
        return role;
    }

    /**
     *
     * @return 返回所有角色
     */
    @Override
    public List<Role> getAllRole() {
        return this.roleDao.getAllRole();
    }
}
