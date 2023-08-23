package com.jch.introductoryadmin.controller;

import com.jch.introductoryadmin.domain.User;
import com.jch.introductoryadmin.service.IUserService;
import com.jch.introductorycommom.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * (User)表控制层
 *
 * @author 吉晨浩
 * @since 2023-08-20 15:33:05
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageNo 起始页
     * @param pageSize 每页大小
     * @return 查询结果
     */
    @GetMapping("/list")
    public ResultUtil<Page<User>> queryByPage(User user,@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        return ResultUtil.ok(this.userService.queryByPage(user, pageNo, pageSize));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ResultUtil<User> queryById(@PathVariable("id") Integer id) {
        return ResultUtil.ok(this.userService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param user 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public ResultUtil<User> add(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResultUtil.ok(this.userService.insert(user));
    }

    /**
     * 编辑数据
     *
     * @param user 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public ResultUtil<User> edit(@RequestBody User user) {
        return ResultUtil.ok(this.userService.update(user));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById/{id}")
    public ResultUtil<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResultUtil.ok(this.userService.deleteById(id));
    }

    /**
     * 根据token获取用户信息
     * @param token
     * @return 用户信息
     */
    @GetMapping("/info")
    public ResultUtil<Map<String, Object>> getUserinfo(@RequestParam("token") String token) {
        // 根据token获取用户信息
        Map<String, Object> data = userService.getUserinfo(token);
        if (data == null) {
            return ResultUtil.fail("301","登录信息无效, 请重新登录");
        }
        return ResultUtil.ok(data);
    }
}

