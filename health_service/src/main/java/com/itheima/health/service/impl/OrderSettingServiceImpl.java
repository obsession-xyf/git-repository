package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.OrderSettingDao;
import com.itheima.health.pojo.OrderSetting;
import com.itheima.health.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                int number = orderSetting.getNumber();//可预约人数
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

    /**
     * 根据日期查询预约设置数据
     * @param month
     * @return
     */
    @Override
    public List<Map<String, Integer>> getOrderSettingByMonth(String month) {
        // 1.组织查询Map，dateBegin表示月份开始时间，dateEnd月份结束时间
        String dateBegin = month + "-1";//2019-03-1
        String dateEnd = month + "-31";//2019-03-31
        Map map = new HashMap();
        map.put("dateBegin",dateBegin);
        map.put("dateEnd",dateEnd);
        // 2.查询当前月份的预约设置
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);
        List<Map<String, Integer>> data = new ArrayList<>();
        // 3.将List<OrderSetting>，组织成List<Map>
        for (OrderSetting orderSetting : list) {
            Map orderSettingMap = new HashMap();
            orderSettingMap.put("date",orderSetting.getOrderDate().getDate());//获得日期（几号）
            orderSettingMap.put("number",orderSetting.getNumber());//可预约人数
            orderSettingMap.put("reservations",orderSetting.getReservations());//已预约人数
            data.add(orderSettingMap);
        }
        return data;
    }

    /**
     * 通过日期修改最大预约数
     * @param orderSetting
     */
    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        // 查询是否已经存在
        OrderSetting os = orderSettingDao.findByOrderDate(orderSetting.getOrderDate());
        if (os != null) {
            // 存在 判断预约人数是否  最大预约数
            int number = orderSetting.getNumber();//可预约人数
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
