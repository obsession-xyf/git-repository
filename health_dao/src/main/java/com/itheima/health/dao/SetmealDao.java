package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SetmealDao {
    /**
     * 添加套餐信息
     *
     * @param setmeal
     */
    void add(Setmeal setmeal);

    /**
     * 添加关系
     *
     * @param setmealId
     * @param checkgroupId
     */
    void addSetmealCheckgroup(@Param("setmealId") Integer setmealId, @Param("checkgroupId") Integer checkgroupId);

    /**
     * 查询分页-套餐列表
     *
     * @param queryString
     * @return
     */
    Page<Setmeal> findAllByCondition(@Param("queryString") String queryString);

    /**
     * 根据id查询套餐
     *
     * @param id
     * @return
     */
    Setmeal findById(@Param("id") int id);

    /**
     * 根据套餐id查询此套餐下包含的检查组的id
     *
     * @param id
     * @return
     */
    List<Integer> findCheckgroupIdsBySetmealId(@Param("id") int id);

    /**
     * 是否有订单使用了此套餐
     *
     * @param id
     * @return
     */
    int findCountBySetmealId(@Param("id") int id);

    /**
     * 删除套餐与检查组的关系
     *
     * @param id
     */
    void deleteBySetmealId(@Param("id") int id);

    /**
     * 删除套餐
     *
     * @param id
     */
    void deleteSetmeal(@Param("id") int id);

    /**
     * 查询所有套餐
     *
     * @return
     */
    List<Setmeal> findAll();

    /**
     * 查询详情页
     *
     * @param id
     * @return
     */
    Setmeal findDetailById(@Param("id") int id);

    /**
     * 修改套餐信息
     * @param setmeal
     */
    void update(Setmeal setmeal);

    /**
     * 根据套餐id删除旧关系
     * @param id
     */
    void deleteSetmealCheckgroup(@Param("id") Integer id);
}
