package com.jch.introductoryadmin.controller;

import com.jch.introductoryadmin.domain.Role;
import com.jch.introductoryadmin.service.IRoleService;
import com.jch.introductorycommom.utils.ResultUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * (Role)表控制层
 *
 * @author 吉晨浩
 * @since 2023-08-23 10:36:17
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    /**
     * 服务对象
     */
    @Autowired
    private IRoleService roleService;
    
   /**
     * 分页查询
     *
     * @param role 筛选条件
     * @param pageNo 起始页
     * @param pageSize 每页大小              
     * @return 查询结果
     */
    @GetMapping("/list")
    public ResultUtil<Page<Role>> queryByPage(Role role, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        return ResultUtil.ok(this.roleService.queryByPage(role, pageNo, pageSize));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ResultUtil<Role> queryById(@PathVariable("id") Integer id) {
        return ResultUtil.ok(this.roleService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param role 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResultUtil<Role> add(@RequestBody Role role) {
        return ResultUtil.ok(this.roleService.insert(role));
    }

    /**
     * 编辑数据
     *
     * @param role 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public ResultUtil<Role> edit(@RequestBody Role role) {
        return ResultUtil.ok(this.roleService.update(role));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById/{id}")
    public ResultUtil<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResultUtil.ok(this.roleService.deleteById(id));
    }

}

