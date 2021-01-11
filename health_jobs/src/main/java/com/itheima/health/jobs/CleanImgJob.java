package com.itheima.health.jobs;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.service.SetmealService;
import com.itheima.health.utils.QiNiuUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: xieyufeng
 * @Date: 2021/1/9 9:10 上午
 */
@Component
public class CleanImgJob {

    private static final Logger log = LoggerFactory.getLogger(CleanImgJob.class);

    /**
     * 订阅服务
     */
    @Reference
    private SetmealService setmealService;

    /**
     * 清理垃圾图片
     * initialDelay: 启动时延迟多少毫秒后才执行
     * fixedDelay: 每间隔多长时间执行
     */
    @Scheduled(initialDelay = 1000, fixedDelay = 60000)
    public void cleanImg() {
        log.info("开始执行清理垃圾图片。。。");
        // 调用QiNiuUtils.查询所有图片
        List<String> imgIn7Niu = QiNiuUtils.listFile();
        log.debug("七牛上一共有{}张图片", imgIn7Niu.size());
        // 调用setmealService查询数据库的所有图片
        List<String> imgInDb = setmealService.findImgs();
        log.debug("数据库一共有{}张图片", imgInDb.size());
        // imgIn7Niu-imgInDb
        imgIn7Niu.removeAll(imgInDb);
        if (imgIn7Niu.size() > 0) {
            log.info("要清理的图片有{}张", imgIn7Niu.size());
            // 调用7牛工具删除垃圾图片
            QiNiuUtils.removeFiles(imgIn7Niu.toArray(new String[]{}));
        }
        log.info("清理完成。。。");
    }
}
