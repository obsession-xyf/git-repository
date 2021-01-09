package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckItem;

import java.util.List;

/**
 * @Author: xieyufeng
 * @Date: 2021/1/5 1:32 下午
 */
public interface CheckItemService {

    /**
     * 查询所有检查项
     *
     * @return
     */
    List<CheckItem> findAll();

    /**
     * 添加检查项
     *
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 通过id删除检查项
     *
     * @param id
     */
    void deleteById(int id) throws Exception;

    /**
     * 通过id查询检查项
     *
     * @param id
     * @return
     */
    CheckItem findById(int id);

    /**
     * 修改检查项
     *
     * @param checkItem
     */
    void update(CheckItem checkItem);

    /**
     * 分页查询检查项
     *
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean);
}
