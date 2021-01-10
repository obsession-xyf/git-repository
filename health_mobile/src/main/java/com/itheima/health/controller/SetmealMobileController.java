package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetmealService;
import com.itheima.health.utils.QiNiuUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: xieyufeng
 * @Date: 2021/1/10 9:19 上午
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealMobileController {
    /**
     * 订阅服务
     */
    @Reference
    private SetmealService setmealService;

    /**
     * 查询所有套餐
     *
     * @return
     */
    @GetMapping("/getSetmeal")
    public Result getSetmeal() {
        // 查询所有套餐
        List<Setmeal> list = setmealService.findAll();
        // 重新设置图片路径，即拼接域名
        list.forEach(s -> s.setImg(QiNiuUtils.DOMAIN + s.getImg()));
        return new Result(true, MessageConstant.GET_SETMEAL_LIST_SUCCESS, list);
    }

    /**
     * 查询详情页
     *
     * @return
     */
    @GetMapping("/findDetailById")
    public Result findDetailById(int id) {
        // 调用服务层
        Setmeal setmeal = setmealService.findDetailById(id);
        // 拼接域名
        setmeal.setImg(QiNiuUtils.DOMAIN + setmeal.getImg());
        return new Result(true, "", setmeal);
    }

}
