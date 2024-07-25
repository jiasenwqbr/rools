package org.jeecg.modules.demo.dashboard.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.demo.dashboard.vo.CardData;
import org.jeecg.modules.demo.datadrlobject.service.IRoolDrlObjectService;
import org.jeecg.modules.demo.datadslobject.service.IRoolDslObjectService;
import org.jeecg.modules.demo.dataobject.service.IRoolDataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: 贾森
 * @date: 2024年07月24日 15:57
 */
@Api(tags="规则文件DRL")
@RestController
@RequestMapping("/dashboard")
@Slf4j
public class DashBoardController {
    @Autowired
    private IRoolDataObjectService roolDataObjectService;
    @Autowired
    private IRoolDrlObjectService roolDrlObjectService;
    @Autowired
    private IRoolDslObjectService roolDslObjectService;

    @GetMapping("/getGroupCardData")
    public Result<List<CardData>> getGroupCardData(){

        long dataObjectCount = roolDataObjectService.count();
        long drlCount = roolDrlObjectService.count();
        long dslCount = roolDslObjectService.count();
        long allCount = dataObjectCount + drlCount + dslCount;
        Result<List<CardData>> result = new Result<List<CardData>>();
        List<CardData> list = new ArrayList<>();
        CardData cd1 = new CardData();
        cd1.setTitle("规则数");
        cd1.setIcon("visit-count|svg");
        cd1.setValue(allCount);
        cd1.setTotal(allCount);
        cd1.setColor("green");
        cd1.setAction("月");

        CardData cd2 = new CardData();
        cd2.setTitle("数据对象");
        cd2.setIcon("total-sales|svg");
        cd2.setValue(dataObjectCount);
        cd2.setTotal(dataObjectCount);
        cd2.setColor("blue");
        cd2.setAction("月");

        CardData cd3 = new CardData();
        cd3.setTitle("DRL");
        cd3.setIcon("download-count|svg");
        cd3.setValue(drlCount);
        cd3.setTotal(drlCount);
        cd3.setColor("orange");
        cd3.setAction("月");

        CardData cd4 = new CardData();
        cd4.setTitle("DSL");
        cd4.setIcon("transaction|svg");
        cd4.setValue(dslCount);
        cd4.setTotal(dslCount);
        cd4.setColor("purple");
        cd4.setAction("月");

        list.add(cd1);
        list.add(cd2);
        list.add(cd3);
        list.add(cd4);
        result.setResult(list);

        return result;
    }
}
