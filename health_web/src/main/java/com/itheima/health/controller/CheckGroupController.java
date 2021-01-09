package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.service.CheckGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: xieyufeng
 * @Date: 2021/1/7 11:59 上午
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;

    /**
     * 查询检查组分页信息
     *
     * @param queryPageBean
     * @return
     */
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        // 调用业务层查询分页信息
        PageResult<CheckGroup> checkGroupPageResult = checkGroupService.findPage(queryPageBean);
        // 返回结果
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroupPageResult);
    }

    /**
     * 添加检查组
     *
     * @param checkGroup
     * @param checkitemIds
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {
        // 调用业务层
        checkGroupService.add(checkGroup, checkitemIds);
        // 返回结果
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    /**
     * 通过id查看检查组
     *
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public Result findById(int id) {
        // 调用业务层
        CheckGroup checkGroup = checkGroupService.findById(id);
        // 返回结果
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroup);
    }

    /**
     * 查询检查组包含的检查项的id
     *
     * @param id
     * @return
     */
    @GetMapping("/findItemIdsByGroupId")
    public Result findItemIdsByGroupId(int id) {
        // 调用业务层
        List<Integer> itemIds = checkGroupService.findItemIdsByGroupId(id);
        // 返回结果
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, itemIds);
    }

    /**
     * 修改检查组
     *
     * @param checkGroup
     * @param checkitemIds
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {
        // 调用业务层
        checkGroupService.update(checkGroup, checkitemIds);
        // 返回结果
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

    /**
     * 删除检查组
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public Result delete(int id) {
        try {
            // 调用业务层
            checkGroupService.delete(id);
            // 返回结果
            return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }


    /**
     * xxx
     * @return
     */
    @GetMapping("findAll")
    public Result findAll() {
        List<CheckGroup> checkGroupList = checkGroupService.findAll();
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS ,checkGroupList);
    }
}
