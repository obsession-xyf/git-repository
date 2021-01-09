package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.itheima.health.dao.CheckItemDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckItemService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: xieyufeng
 * @Date: 2021/1/5 1:34 下午
 */
@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    @Transactional
    public void deleteById(@Param("id") int id) throws Exception {
        int count = checkItemDao.findCountByCheckItemId(id);
        if (count > 0) {
            // 抛自定义异常
            System.out.println(count);
            throw new RuntimeException();
        }
        checkItemDao.deleteById(id);
    }

    @Override
    public CheckItem findById(int id) {
        return checkItemDao.findById(id);
    }

    @Override
    public void update(CheckItem checkItem) {
        checkItemDao.update(checkItem);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        // 设置当前页面，以及每页条数
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // 按条件查询所有
        if (StringUtil.isNotEmpty(queryPageBean.getQueryString())) {
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        List<CheckItem> checkItemList = checkItemDao.findAllByCondition(queryPageBean.getQueryString());

        // 转换成带有分页信息的对象
        PageInfo<CheckItem> pageInfo = new PageInfo<>(checkItemList);

        /*// 封装到分页结果对象中
        Page<CheckItem> pageResult = (Page<CheckItem>) checkItemDao.findAllByCondition(queryPageBean.getQueryString());
        List<CheckItem> checkItems = pageResult.getResult();
        long total = pageResult.getTotal();*/

        // 使用PageResult封装分页信息返回
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }
}
