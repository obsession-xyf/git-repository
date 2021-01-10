package com.itheima.health.service;

import com.itheima.health.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {

    /**
     * 将excel中的数据上传到数据库
     *
     * @param orderSettingList
     */
    void add(List<OrderSetting> orderSettingList) throws Exception;

    /**
     * 通过月份查询预约设置信息
     * @param month
     * @return
     */
    List<Map<String, Integer>> getOrderSettingByMonth(String month);
}
