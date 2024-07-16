package org.jeecg.modules.demo.record.service.impl;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.common.util.CommonUtils;
import org.jeecg.modules.demo.record.entity.RoolApiLog;
import org.jeecg.modules.demo.record.mapper.RoolApiLogMapper;
import org.jeecg.modules.demo.record.service.IRoolApiLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: 贾森
 * @date: 2024年07月16日 上午9:25
 */
@Service
public class RoolApiLogServiceImpl extends ServiceImpl<RoolApiLogMapper,RoolApiLog> implements IRoolApiLogService {
   @Resource RoolApiLogMapper roolApiLogMapper;
    @Override
    public void removeAll() {
        roolApiLogMapper.removeAll();
    }

    @Override
    public Long findTotalVisitCount() {
        return roolApiLogMapper.findTotalVisitCount();
    }

    @Override
    public Long findTodayVisitCount(Date dayStart, Date dayEnd) {
        return roolApiLogMapper.findTodayVisitCount(dayStart,dayEnd);
    }

    @Override
    public Long findTodayIp(Date dayStart, Date dayEnd) {
        return roolApiLogMapper.findTodayIp(dayStart,dayEnd);
    }

    @Override
    public List<Map<String, Object>> findVisitCount(Date dayStart, Date dayEnd) {
        DbType dbType = CommonUtils.getDatabaseTypeEnum();
        return roolApiLogMapper.findVisitCount(dayStart,dayEnd,dbType.getDb());
    }
}
