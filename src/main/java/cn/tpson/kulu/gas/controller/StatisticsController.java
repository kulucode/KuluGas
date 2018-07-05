package cn.tpson.kulu.gas.controller;

import cn.tpson.kulu.gas.service.StatisticsService;
import cn.tpson.kulu.gas.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Zhangka in 2018/07/05
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    /**
     * 统计数据.
     * @return
     */
    @GetMapping("/charts")
    public ResultVO charts() {
        return ResultVO.successResult(statisticsService.statistics());
    }
}
