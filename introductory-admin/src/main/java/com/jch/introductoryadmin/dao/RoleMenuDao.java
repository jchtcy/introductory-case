package com.jch.introductoryadmin.dao;

import com.jch.introductoryadmin.domain.RoleMenu;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (RoleMenu)表数据库访问层
 *
 * @author 吉晨浩
 * @since 2023-08-20 19:08:40
 */
public interface RoleMenuDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RoleMenu queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param roleMenu 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<RoleMenu> queryAllByLimit(RoleMenu roleMenu, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param roleMenu 查询条件
     * @return 总行数
     */
    long count(RoleMenu roleMenu);

    /**
     * 新增数据
     *
     * @param roleMenu 实例对象
     * @return 影响行数
     */
    int insert(RoleMenu roleMenu);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<RoleMenu> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<RoleMenu> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<RoleMenu> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<RoleMenu> entities);

    /**
     * 修改数据
     *
     * @param roleMenu 实例对象
     * @return 影响行数
     */
    int update(RoleMenu roleMenu);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 返回角色拥有的菜单
     * @param roleId 角色id
     * @return
     */
    List<Integer> getMenuIdListByRoleId(Integer roleId);

    /**
     * 通过角色id删除数据
     *
     * @param roleId 角色id
     * @return 影响行数
     */
    int deleteByroleId(Integer roleId);

    /**
     * 批量插入角色权限
     * @param roleId
     * @param menuIdList
     */
    int insertBatchMenuIdList(Integer roleId, List<Integer> menuIdList);
}

