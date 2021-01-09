package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.OrderSetting;
import com.itheima.health.service.OrderSettingService;
import com.itheima.health.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: xieyufeng
 * @Date: 2021/1/9 4:44 下午
 */
@RestController
@RequestMapping("ordersetting")
public class OrderSettingController {
    @Reference
    private OrderSettingService orderSettingService;

    /**
     * 上传excel文件
     *
     * @return
     */
    @RequestMapping("xxx")
    public Result xxx(MultipartFile excelFile) {
        try {
            // 读取excel文件
            List<String[]> strings = POIUtils.readExcel(excelFile);
            // List<String[]> 转换为 List<OrderSetting>
            List<OrderSetting> orderSettingList = new ArrayList<>();
            // 遍历strings得到每行的数据 每行的数据是String[]
            for (String[] dataArr : strings) {
                // 格式
                SimpleDateFormat sdf = new SimpleDateFormat(POIUtils.DATE_FORMAT);
                Date orderDate = sdf.parse(dataArr[0]);
                int number = Integer.parseInt(dataArr[1]);
                OrderSetting orderSetting = new OrderSetting(orderDate, number);
                // 添加行数据即orderSetting对象到集合
                orderSettingList.add(orderSetting);
            }
            // 调用服务层
            orderSettingService.add(orderSettingList);
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_FAIL);

        }

    }
}
