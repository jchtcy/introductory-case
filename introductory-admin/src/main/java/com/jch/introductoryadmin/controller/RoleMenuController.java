package com.jch.introductoryadmin.controller;

import com.jch.introductoryadmin.domain.RoleMenu;
import com.jch.introductoryadmin.service.IRoleMenuService;
import com.jch.introductorycommom.utils.ResultUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * (RoleMenu)表控制层
 *
 * @author 吉晨浩
 * @since 2023-08-23 10:36:17
 */
@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController {
    /**
     * 服务对象
     */
    @Autowired
    private IRoleMenuService roleMenuService;
    
   /**
     * 分页查询
     *
     * @param roleMenu 筛选条件
     * @param pageNo 起始页
     * @param pageSize 每页大小              
     * @return 查询结果
     */
    @GetMapping("/list")
    public ResultUtil<Page<RoleMenu>> queryByPage(RoleMenu roleMenu, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        return ResultUtil.ok(this.roleMenuService.queryByPage(roleMenu, pageNo, pageSize));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ResultUtil<RoleMenu> queryById(@PathVariable("id") Integer id) {
        return ResultUtil.ok(this.roleMenuService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param roleMenu 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResultUtil<RoleMenu> add(@RequestBody RoleMenu roleMenu) {
        return ResultUtil.ok(this.roleMenuService.insert(roleMenu));
    }

    /**
     * 编辑数据
     *
     * @param roleMenu 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public ResultUtil<RoleMenu> edit(@RequestBody RoleMenu roleMenu) {
        return ResultUtil.ok(this.roleMenuService.update(roleMenu));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById/{id}")
    public ResultUtil<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResultUtil.ok(this.roleMenuService.deleteById(id));
    }

}

