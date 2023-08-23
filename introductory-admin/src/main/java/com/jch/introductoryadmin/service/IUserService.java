package com.jch.introductoryadmin.service;

import com.jch.introductoryadmin.domain.Role;
import com.jch.introductoryadmin.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * (User)表服务接口
 *
 * @author 吉晨浩
 * @since 2023-08-21 09:53:31
 */
public interface IUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageNo 起始页
     * @param pageSize 每页大小
     * @return 查询结果
     */
    Page<User> queryByPage(User user, int pageNo, int pageSize);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 根据用户名查询用户
     * @param username
     * @return 用户信息
     */
    User selectByUsername(String username);

    /**
     * 根据token获取用户信息
     * @param token
     * @return 用户信息
     */
    Map<String, Object> getUserinfo(String token);

    /**
     * 通过用户id查找用户权限
     * @param userId
     * @return List<Role>
     */
    List<Role> getUserRole (Integer userId);
}
