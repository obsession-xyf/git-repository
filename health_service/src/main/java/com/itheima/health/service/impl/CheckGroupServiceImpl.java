package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.dao.CheckGroupDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: xieyufeng
 * @Date: 2021/1/7 12:07 下午
 */
@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;

    /**
     * 查询检查组分页信息
     *
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean) {
        //分页小助手
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        // 判断查询条件是否为空
        if (StringUtil.isNotEmpty(queryPageBean.getQueryString())) {
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }

        // Page <E> extends java.util.ArrayList<E> 封装了Page的信息
        Page<CheckGroup> checkGroups = checkGroupDao.findAllByCondition(queryPageBean.getQueryString());

        // 封装到PageResult对象中返回
        return new PageResult<CheckGroup>(checkGroups.getTotal(), checkGroups.getResult());
    }

    /**
     * 添加检查组
     *
     * @param checkGroup
     * @param checkitemIds
     */
    @Override
    @Transactional
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        // 添加检查组信息
        checkGroupDao.add(checkGroup);
        // 获取检查组的id
        Integer checkGroupId = checkGroup.getId();
        // 遍历检查项id, 添加检查组与检查项的关系
        if (checkitemIds != null) {
            for (Integer checkitemId : checkitemIds) {
                checkGroupDao.addCheckGroupCheckItem(checkGroupId, checkitemId);
            }
        }
    }

    /**
     * 通过id查看检查组
     *
     * @param id
     * @return
     */
    @Override
    public CheckGroup findById(int id) {
        return checkGroupDao.findById(id);
    }

    /**
     * 查询检查组包含的检查项的id
     *
     * @param id
     * @return
     */
    @Override
    public List<Integer> findItemIdsByGroupId(int id) {
        return checkGroupDao.findItemIdsByGroupId(id);
    }

    /**
     * 修改检查组
     *
     * @param checkGroup
     * @param checkitemIds
     * @return
     */
    @Override
    public void update(CheckGroup checkGroup, Integer[] checkitemIds) {
        //修改检查组信息
        checkGroupDao.update(checkGroup);
        //删除关联的检查项信息
        checkGroupDao.deleteCheckItemIdByCheckGroupId(checkGroup.getId());
        //添加新的关联的检查项信息
        for (Integer checkitemId : checkitemIds) {
            checkGroupDao.addCheckGroupCheckItem(checkGroup.getId(), checkitemId);
        }
    }

    /**
     * 删除检查组
     *
     * @param id
     */
    @Override
    @Transactional
    public void delete(int id) throws Exception{
        // 检查 这个检查组是否被套餐使用了
        int count = checkGroupDao.findSetmealCountByCheckGroupId(id);
        if(count > 0){
            // 被使用了
            throw new RuntimeException(MessageConstant.CHECKGROUP_IN_USE);
        }

        //删除旧关系
        checkGroupDao.deleteCheckItemCheckGroup(id);

        // 删除检查组
        checkGroupDao.deleteCheckGroupById(id);
    }

    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }


}
