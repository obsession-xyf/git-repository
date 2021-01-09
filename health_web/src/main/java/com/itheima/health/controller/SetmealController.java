package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Setmeal;
import com.itheima.health.service.SetmealService;
import com.itheima.health.utils.QiNiuUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: xieyufeng
 * @Date: 2021/1/8 10:20 上午
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;

    /**
     * 根据id删除套餐
     * @return
     */
    @RequestMapping("delete")
    public Result delete(int id) {
        try {
            setmealService.delete(id);
            return new Result(true, "删除套餐成功");
        } catch (Exception e) {
            return new Result(false, "删除套餐失败");
        }
    }

    /**
     * 根据id查询套餐
     *
     * @return
     */
    @GetMapping("findById")
    public Result findById(int id) {
        Setmeal setmeal = setmealService.findById(id);
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);
    }

    /**
     * 根据套餐id查询此套餐下包含的检查组的id
     * @return
     */
    @RequestMapping("findCheckgroupIdsBySetmealId")
    public Result findCheckgroupIdsBySetmealId(int id) {
        List<Integer> checkgroupIds = setmealService.findCheckgroupIdsBySetmealId(id);
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, checkgroupIds);
    }

    /**
     * 查询分页-套餐列表
     *
     * @return
     */
    @PostMapping("findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult<Setmeal> setmealPageResult = setmealService.findPage(queryPageBean);
        return new Result(true, MessageConstant.QUERY_SETMEALLIST_SUCCESS, setmealPageResult);
    }

    /**
     * 添加套餐
     *
     * @return
     */
    @RequestMapping("add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds) {
        setmealService.add(setmeal, checkgroupIds);
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    /**
     * 套餐上传图片
     *
     * @return
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile imgFile) {
        // 原始文件名
        String originalFilename = imgFile.getOriginalFilename();
        // 后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 唯一文件名+后缀名
        String uniqueFilename = UUID.randomUUID() + suffix;

        try {
            // 上传到七牛云
            QiNiuUtils.uploadViaByte(imgFile.getBytes(), uniqueFilename);

            // 封装返回数据
            //{
            //    flag:
            //    message:
            //    data:{
            //        imgName: 图片名,
            //        domain: QiNiuUtils.DOMAIN
            //    }
            //}
            Map<String, String> map = new HashMap<String, String>(2);
            map.put("imgName", uniqueFilename);
            map.put("domain", QiNiuUtils.DOMAIN);

            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, map);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
    }
}
