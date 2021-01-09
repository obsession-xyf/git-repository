package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.itheima.health.constant.MessageConstant.*;

/**
 * @Author: xieyufeng
 * @Date: 2021/1/5 1:18 下午
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    @Reference//@Reference注入的是分布式中的远程服务对象，@Resource和@Autowired注入的是本地spring容器中的对象。
    private CheckItemService checkItemService;

    /**
     * 查询所有检查项
     *
     * @return
     */
    @GetMapping("/findAll")
    public Result findAll() {
        List<CheckItem> checkItemList = checkItemService.findAll();
        return new Result(true, QUERY_CHECKITEM_SUCCESS, checkItemList);
    }

    /**
     * 查询分页检查项
     *
     * @return
     */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = checkItemService.findPage(queryPageBean);
        return new Result(true, QUERY_CHECKITEM_SUCCESS, pageResult);
    }

    /**
     * 添加检查项
     *
     * @param checkItem
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem) {
        checkItemService.add(checkItem);
        return new Result(true, ADD_CHECKITEM_SUCCESS);
    }

    /**
     * 通过id删除检查项
     *
     * @param id
     * @return
     */
    @GetMapping("/delete")
    public Result delete(int id) {
        try {
            checkItemService.deleteById(id);
            return new Result(true, DELETE_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, DELETE_CHECKITEM_FAIL);
        }
    }

    /**
     * 通过id查询检查项
     *
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public Result findById(int id) {
        CheckItem checkItem = checkItemService.findById(id);
        return new Result(true, QUERY_CHECKITEM_SUCCESS, checkItem);
    }

    /**
     * 修改检查项
     *
     * @param checkItem
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody CheckItem checkItem) {
        System.out.println(checkItem);
        checkItemService.update(checkItem);
        return new Result(true, EDIT_CHECKITEM_SUCCESS);
    }


}

