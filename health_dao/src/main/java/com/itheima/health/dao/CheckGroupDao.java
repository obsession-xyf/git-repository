package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckGroupDao {
    /**
     * 通过条件查询
     *
     * @param queryString
     * @return
     */
    Page<CheckGroup> findAllByCondition(@Param("queryString") String queryString);

    /**
     * 添加检查组
     *
     * @param checkGroup
     */
    void add(CheckGroup checkGroup);

    /**
     * 添加检查组、检查项的关联
     *
     * @param checkGroupId
     * @param checkitemId
     */
    void addCheckGroupCheckItem(@Param("checkGroupId") Integer checkGroupId, @Param("checkitemId") Integer checkitemId);

    /**
     * 通过id查看检查组
     *
     * @param id
     * @return
     */
    CheckGroup findById(@Param("id") int id);

    /**
     * 查询检查组包含的检查项的id
     *
     * @param id
     * @return
     */
    List<Integer> findItemIdsByGroupId(@Param("id") int id);

    /**
     * 修改检查组
     *
     * @param checkGroup
     */
    void update(CheckGroup checkGroup);

    /**
     * 删除关联的检查项信息
     *
     * @param checkgroup_id
     */
    void deleteCheckItemIdByCheckGroupId(@Param("checkgroup_id") Integer checkgroup_id);

    /**
     * 删除检查组
     * @param id
     */
    void deleteCheckGroupById(@Param("id") int id);

    /**
     * 查询关联此检查组的套餐的数量
     * @param id
     * @return
     */
    int findSetmealCountByCheckGroupId(@Param("id") int id);

    /**
     * 删除旧关系
     * @param id
     */
    void deleteCheckItemCheckGroup(@Param("id") int id);

    /**
     * 查询所有检查组
     * @return
     */
    List<CheckGroup> findAll();
}
