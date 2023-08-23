package com.jch.introductoryadmin.controller;

import com.jch.introductoryadmin.domain.Menu;
import com.jch.introductoryadmin.service.IMenuService;
import com.jch.introductorycommom.utils.ResultUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * (Menu)表控制层
 *
 * @author 吉晨浩
 * @since 2023-08-23 10:36:17
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    /**
     * 服务对象
     */
    @Autowired
    private IMenuService menuService;
    
   /**
     * 分页查询
     *
     * @param menu 筛选条件
     * @param pageNo 起始页
     * @param pageSize 每页大小              
     * @return 查询结果
     */
    @GetMapping("/list")
    public ResultUtil<Page<Menu>> queryByPage(Menu menu, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        return ResultUtil.ok(this.menuService.queryByPage(menu, pageNo, pageSize));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public ResultUtil<Menu> queryById(@PathVariable("id") Integer id) {
        return ResultUtil.ok(this.menuService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param menu 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResultUtil<Menu> add(@RequestBody Menu menu) {
        return ResultUtil.ok(this.menuService.insert(menu));
    }

    /**
     * 编辑数据
     *
     * @param menu 实体
     * @return 编辑结果
     */
    @PutMapping("/edit")
    public ResultUtil<Menu> edit(@RequestBody Menu menu) {
        return ResultUtil.ok(this.menuService.update(menu));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/deleteById/{id}")
    public ResultUtil<Boolean> deleteById(@PathVariable("id") Integer id) {
        return ResultUtil.ok(this.menuService.deleteById(id));
    }

}

