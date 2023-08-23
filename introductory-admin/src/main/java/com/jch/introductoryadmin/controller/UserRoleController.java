package com.jch.introductoryadmin.controller;

import com.jch.introductoryadmin.domain.UserRole;
import com.jch.introductoryadmin.service.IUserRoleService;
import com.jch.introductorycommom.utils.ResultUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * (UserRole)表控制层
 *
 * @author 吉晨浩
 * @since 2023-08-23 10:36:17
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController {
    /**
     * 服务对象
     */
    @Autowired
    private IUserRoleService userRoleService;
    
   /**
     * 分页查询
     *
     * @param userRole 筛选条件
     * @param pageNo 起始页
     * @param pageSize 每页大小              
     * @return 查询结果
     */
    @GetMapping("/list")
    public ResultUtil<Page<UserRole>> queryByPage(UserRole userRole, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        return ResultUtil.ok(this.userRoleService.queryByPage(userRole, pageNo, pageSize));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ResultUtil<UserRole> queryById(@PathVariable("id") Integer id) {
        return ResultUtil.ok(this.userRoleService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param userRole 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResultUtil<UserRole> add(@RequestBody UserRole userRole) {
        return ResultUtil.ok(this.userRoleService.insert(userRole));
    }

    /**
     * 编辑数据
     *
     * @param userRole 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public ResultUtil<UserRole> edit(@RequestBody UserRole userRole) {
        return ResultUtil.ok(this.userRoleService.update(userRole));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById/{id}")
    public ResultUtil<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResultUtil.ok(this.userRoleService.deleteById(id));
    }

}

