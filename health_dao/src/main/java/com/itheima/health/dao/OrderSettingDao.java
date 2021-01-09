package com.itheima.health.dao;

import com.itheima.health.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface OrderSettingDao {
    /**
     * 通过日期查询预约设置信息
     * @param orderDate
     * @return
     */
    OrderSetting findByOrderDate(@Param("orderDate") Date orderDate);

    /**
     * 修改预约设置
     * @param orderSetting
     */
    void update(OrderSetting orderSetting);

    /**
     * 添加预约设置
     * @param orderSetting
     */
    void add(OrderSetting orderSetting);


}
