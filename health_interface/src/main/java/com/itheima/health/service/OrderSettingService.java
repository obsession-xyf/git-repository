package com.itheima.health.service;

import com.itheima.health.pojo.OrderSetting;

import java.util.List;

public interface OrderSettingService {

    /**
     * 将excel中的数据上传到数据库
     * @param orderSettingList
     */
    void add(List<OrderSetting> orderSettingList);
}
