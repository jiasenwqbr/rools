package org.jeecg.modules.demo.record.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.record.entity.RoolApiLog;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IRoolApiLogService extends IService<RoolApiLog> {
    /**
     * 清空所有日志记录
     */
    public void removeAll();

    /**
     * 获取系统总访问次数
     *
     * @return Long
     */
    Long findTotalVisitCount();

    /**
     * 获取系统今日访问次数
     * @param dayStart
     * @param dayEnd
     * @return Long
     */
    Long findTodayVisitCount(Date dayStart, Date dayEnd);

    /**
     * 获取系统今日访问 IP数
     * @param dayStart 开始时间
     * @param dayEnd 结束时间
     * @return Long
     */
    Long findTodayIp(Date dayStart, Date dayEnd);
    /**
     *   首页：根据时间统计访问数量/ip数量
     * @param dayStart
     * @param dayEnd
     * @return
     */
    List<Map<String,Object>> findVisitCount(Date dayStart, Date dayEnd);
}
