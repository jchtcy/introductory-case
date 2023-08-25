package com.jch.introductoryadmin.controller;

import com.jch.introductoryadmin.domain.Menu;
import com.jch.introductoryadmin.service.IMenuService;
import com.jch.introductorycommom.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * (Menu)表控制层
 *
 * @author 吉晨浩
 * @since 2023-08-23 10:36:17
 */
@Api(tags = {"菜单接口"})
@RestController
@RequestMapping("/menu")
public class MenuController {
    /**
     * 服务对象
     */
    @Autowired
    private IMenuService menuService;
    
   /**
     * 查询
     *
     * @param menu 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public ResultUtil<List<Menu>> queryList(Menu menu) {
        return ResultUtil.ok(this.menuService.queryList(menu));
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

    /**
     * 查询所有菜单
     *
     * @return 查询结果
     */
    @ApiOperation("获得所欲菜单")
    @GetMapping("/all")
    public ResultUtil<List<Menu>> getAllMenu() {
        List<Menu> menuList = this.menuService.getAllMenu();
        return ResultUtil.ok(menuList);
    }

}

