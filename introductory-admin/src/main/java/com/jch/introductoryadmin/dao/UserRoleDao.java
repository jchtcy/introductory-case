package com.jch.introductoryadmin.dao;

import com.jch.introductoryadmin.domain.UserRole;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (UserRole)表数据库访问层
 *
 * @author 吉晨浩
 * @since 2023-08-20 19:08:40
 */
public interface UserRoleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserRole queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param userRole 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<UserRole> queryAllByLimit(UserRole userRole, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param userRole 查询条件
     * @return 总行数
     */
    long count(UserRole userRole);

    /**
     * 新增数据
     *
     * @param userRole 实例对象
     * @return 影响行数
     */
    int insert(UserRole userRole);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserRole> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserRole> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserRole> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UserRole> entities);

    /**
     * 修改数据
     *
     * @param userRole 实例对象
     * @return 影响行数
     */
    int update(UserRole userRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 批量增加用户权限
     * @param userId
     * @param roleIdList
     */
    void insertBatchRole(Integer userId, List<Integer> roleIdList);

    /**
     * 获取用户权限
     * @param userId
     * @return
     */
    List<Integer> getUserRoles(Integer userId);

    /**
     * 根据用户id删除权限
     * @param userId
     * @return
     */
    int deleteByUserId(Integer userId);
}

