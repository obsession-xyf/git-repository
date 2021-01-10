package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.itheima.health.dao.SetmealDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: xieyufeng
 * @Date: 2021/1/8 6:52 下午
 */
@Service(interfaceClass = SetmealService.class)
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealDao setmealDao;

    /**
     * 添加套餐
     *
     * @param setmeal
     * @param checkgroupIds
     */
    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        // 添加套餐
        setmealDao.add(setmeal);
        // 添加套餐关联的体检组
        for (Integer checkgroupId : checkgroupIds) {
            setmealDao.addSetmealCheckgroup(setmeal.getId(), checkgroupId);
        }

    }

    /**
     * 查询分页-套餐列表
     *
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<Setmeal> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        if (StringUtil.isNotEmpty(queryPageBean.getQueryString())) {
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        // 按条件查询所有
        Page<Setmeal> setmealPage = setmealDao.findAllByCondition(queryPageBean.getQueryString());
        return new PageResult<>(setmealPage.getTotal(), setmealPage.getResult());
    }

    /**
     * 根据id查询套餐
     *
     * @param id
     * @return
     */
    @Override
    public Setmeal findById(int id) {
        return setmealDao.findById(id);
    }

    /**
     * 根据套餐id查询此套餐下包含的检查组的id
     *
     * @param id
     * @return
     */
    @Override
    public List<Integer> findCheckgroupIdsBySetmealId(int id) {
        return setmealDao.findCheckgroupIdsBySetmealId(id);
    }

    /**
     * 根据id删除套餐
     *
     * @param id
     */
    @Override
    public void delete(int id) throws Exception {
        // 是否有订单使用了此套餐
        int count = setmealDao.findCountBySetmealId(id);
        // 有 抛异常
        if (count > 0) {
            throw new RuntimeException("该套餐已被使用过，不能删除！");
        } else {
            // 没有 删除套餐与检查组的关系
            setmealDao.deleteBySetmealId(id);
            //删除套餐
            setmealDao.deleteSetmeal(id);
        }
    }

    /**
     * 查询所有套餐
     *
     * @return
     */
    @Override
    public List<Setmeal> findAll() {
        return setmealDao.findAll();
    }

    /**
     * 查询详情页
     *
     * @param id
     * @return
     */
    @Override
    public Setmeal findDetailById(int id) {
        return setmealDao.findDetailById(id);
    }

    /**
     * 修改套餐信息
     *
     * @param setmeal
     * @param checkgroupIds
     */
    @Override
    public void update(Setmeal setmeal, Integer[] checkgroupIds) {
        // 修改套餐信息
        setmealDao.update(setmeal);
        // 删除旧关系
        setmealDao.deleteSetmealCheckgroup(setmeal.getId());
        // 遍历添加新关系
        for (Integer checkgroupId : checkgroupIds) {
            setmealDao.addSetmealCheckgroup(setmeal.getId(), checkgroupId);
        }
    }
}
