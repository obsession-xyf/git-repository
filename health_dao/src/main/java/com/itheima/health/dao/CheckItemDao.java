package com.itheima.health.dao;

import com.itheima.health.pojo.CheckItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckItemDao {
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
     * 根据id删除检查项
     *
     * @param id
     */
    void deleteById(int id);

    /**
     * 根据id查询检查项
     *
     * @param id
     * @return
     */
    CheckItem findById(@Param("id") int id);

    /**
     * 根据id更新检查项
     * @param checkItem
     */
    void update(CheckItem checkItem);

    /**
     * 查询所有符合条件的检查项
     * @return
     * @param queryString
     */
    List<CheckItem> findAllByCondition(@Param("queryString") String queryString);

    /**
     * 通过检查项id，查询检查组包含此检查项的数量
     * @param id
     * @return
     */
    int findCountByCheckItemId(@Param("id") int id);


}
