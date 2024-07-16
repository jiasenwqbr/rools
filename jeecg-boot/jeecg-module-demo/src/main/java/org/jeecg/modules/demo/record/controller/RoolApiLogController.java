package org.jeecg.modules.demo.record.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.record.entity.RoolApiLog;
import org.jeecg.modules.demo.record.service.IRoolApiLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.jeecg.common.api.vo.Result;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Description:
 * @author: 贾森
 * @date: 2024年07月16日 上午9:20
 */
@RestController
@RequestMapping("/sys/record/apiLog")
@Slf4j
public class RoolApiLogController {
    @Autowired
    private IRoolApiLogService roolApiLogService;
    /**
     * 全部清除
     */
    private static final String ALL_ClEAR = "allclear";

    /**
     * @功能：查询日志记录
     * @param roolApiLog
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<IPage<RoolApiLog>>  queryPageList(RoolApiLog roolApiLog, @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                    @RequestParam(name="pageSize", defaultValue="10") Integer pageSize, HttpServletRequest req){
        Result<IPage<RoolApiLog>> result = new Result<IPage<RoolApiLog>>();
        QueryWrapper<RoolApiLog> queryWrapper = QueryGenerator.initQueryWrapper(roolApiLog,req.getParameterMap());
        Page<RoolApiLog> page = new Page<RoolApiLog>(pageNo,pageSize);
        //日志关键词
        String keyWord = req.getParameter("keyWord");
        if(oConvertUtils.isNotEmpty(keyWord)) {
            queryWrapper.like("log_content",keyWord);
        }
        //TODO 过滤逻辑处理
        //TODO begin、end逻辑处理
        //TODO 一个强大的功能，前端传一个字段字符串，后台只返回这些字符串对应的字段
        //创建时间/创建人的赋值
        IPage<RoolApiLog> pageList = roolApiLogService.page(page, queryWrapper);
//        log.info("查询当前页："+pageList.getCurrent());
//        log.info("查询当前页数量："+pageList.getSize());
//        log.info("查询结果数量："+pageList.getRecords().size());
//        log.info("数据总数："+pageList.getTotal());
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }
    /**
     * @功能：删除单个日志记录
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result<RoolApiLog> delete(@RequestParam(name="id",required=true) String id) {
        Result<RoolApiLog> result = new Result<RoolApiLog>();
        RoolApiLog sysLog = roolApiLogService.getById(id);
        if(sysLog==null) {
            result.error500("未找到对应实体");
        }else {
            boolean ok = roolApiLogService.removeById(id);
            if(ok) {
                result.success("删除成功!");
            }
        }
        return result;
    }
    /**
     * @功能：批量，全部清空日志记录
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteBatch", method = RequestMethod.DELETE)
    //@RequiresPermissions("system:log:deleteBatch")
    public Result<RoolApiLog> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        Result<RoolApiLog> result = new Result<RoolApiLog>();
        if(ids==null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        }else {
            if(ALL_ClEAR.equals(ids)) {
                this.roolApiLogService.removeAll();
                result.success("清除成功!");
            }
            this.roolApiLogService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }
}
