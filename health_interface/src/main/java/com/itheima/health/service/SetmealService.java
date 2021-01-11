package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Setmeal;

import java.util.List;

public interface SetmealService {
    /**
     * 添加套餐
     *
     * @param setmeal
     * @param checkgroupIds
     */
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    /**
     * 查询分页-套餐列表
     *
     * @param queryPageBean
     * @return
     */
    PageResult<Setmeal> findPage(QueryPageBean queryPageBean);

    /**
     * 根据id查询套餐
     *
     * @param id
     * @return
     */
    Setmeal findById(int id);

    /**
     * 根据套餐id查询此套餐下包含的检查组的id
     *
     * @param id
     * @return
     */
    List<Integer> findCheckgroupIdsBySetmealId(int id);

    /**
     * 根据id删除套餐
     *
     * @param id
     */
    void delete(int id) throws Exception;

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
    Setmeal findDetailById(int id);

    /**
     * 修改套餐信息
     * @param setmeal
     * @param checkgroupIds
     */
    void update(Setmeal setmeal, Integer[] checkgroupIds);

    /**
     * 查询套餐图片信息集合
     * @return
     */
    List<String> findImgs();
}
