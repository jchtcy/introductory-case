package com.jch.introductoryadmin.service.impl;

import com.alibaba.fastjson2.JSON;
import com.jch.introductoryadmin.domain.Role;
import com.jch.introductoryadmin.domain.User;
import com.jch.introductoryadmin.dao.UserDao;
import com.jch.introductoryadmin.service.IUserService;
import com.jch.introductorycommom.utils.JwtUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * (User)表服务实现类
 *
 * @author 吉晨浩
 * @since 2023-08-21 09:53:31
 */
@Service("userService")
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private JwtUtil jwtUtil;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageNo 起始页
     * @param pageSize 每页大小
     * @return 查询结果
     */
    @Override
    public Page<User> queryByPage(User user, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        long total = this.userDao.count(user);
        return new PageImpl<>(this.userDao.queryAllByLimit(user, pageable), pageable, total);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }

    /**
     * 通过用户名查用户
     * @param username
     * @return 用户信息
     */
    @Override
    public User selectByUsername(String username) {
        return userDao.selectByUsername(username);
    }

    /**
     * 根据token获取用户信息
     * @param token
     * @return 用户信息
     */
    @Override
    public Map<String, Object> getUserinfo(String token) {
        User loginUser = null;
        try {
            loginUser = jwtUtil.parseToken(token, User.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (loginUser != null) {
            Map<String, Object> data = new HashMap<>();
            data.put("username", loginUser.getUsername());
            data.put("avatar", loginUser.getAvatar());
            List<Role> userRole = userDao.getUserRole(loginUser.getId());
            List<String> descList = userRole.stream().map(Role::getRoleDesc).collect(Collectors.toList());
            data.put("roles", descList);

            return data;
        }
        return null;
    }
    /**
     * 通过用户id查找用户权限
     * @param userId
     * @return List<Role>
     */
    @Override
    public List<Role> getUserRole(Integer userId) {
        return userDao.getUserRole(userId);
    }
}
