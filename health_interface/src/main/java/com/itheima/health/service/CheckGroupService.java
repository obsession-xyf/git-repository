package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    /**
     * 查询检查组分页信息
     *
     * @param queryPageBean
     * @return
     */
    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);

    /**
     * 添加检查组
     *
     * @param checkGroup
     * @param checkitemIds
     */
    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    /**
     * 通过id查看检查组
     *
     * @param id
     * @return
     */
    CheckGroup findById(int id);

    /**
     * 查询检查组包含的检查项的id
     *
     * @param id
     * @return
     */
    List<Integer> findItemIdsByGroupId(int id);

    /**
     * 修改检查组
     *
     * @param checkGroup
     * @param checkitemIds
     * @return
     */
    void update(CheckGroup checkGroup, Integer[] checkitemIds);

    /**
     * 删除检查组
     *
     * @param id
     */
    void delete(int id) throws Exception;

    /**
     * 查询所有检查组
     * @return
     */
    List<CheckGroup> findAll();
}
