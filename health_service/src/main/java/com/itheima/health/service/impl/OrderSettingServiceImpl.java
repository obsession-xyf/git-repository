package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.OrderSettingDao;
import com.itheima.health.pojo.OrderSetting;
import com.itheima.health.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: xieyufeng
 * @Date: 2021/1/9 5:13 下午
 */
@Service(interfaceClass = OrderSettingService.class)
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingDao orderSettingDao;

    /**
     * 添加excel
     *
     * @param orderSettingList
     */
    @Override
    @Transactional
    public void add(List<OrderSetting> orderSettingList) throws Exception{
        // 遍历
        for (OrderSetting orderSetting : orderSettingList) {
            // 查询是否已经存在
            OrderSetting os = orderSettingDao.findByOrderDate(orderSetting.getOrderDate());
            if (os != null) {
                // 存在 判断预约人数是否  最大预约数
                int number = os.getNumber();//可预约人数
                int reservations = os.getReservations();//已预约人数
                if (number < reservations) {
                    throw new RuntimeException("可预约人数不能小于已预约人数");
                } else {
                    // 修改
                    orderSettingDao.update(orderSetting);
                }
            } else {
                // 不存在，直接添加
                orderSettingDao.add(orderSetting);
            }

        }
    }
}
