package cn.tpson.kulu.gas.controller;

import cn.tpson.kulu.gas.constant.EqpTypeEnum;
import cn.tpson.kulu.gas.dto.*;
import cn.tpson.kulu.gas.query.EqpPunishQuery;
import cn.tpson.kulu.gas.service.EqpPunishService;
import cn.tpson.kulu.gas.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Zhangka in 2018/07/04
 */
@RestController
@RequestMapping("/punish")
public class PunishController extends BaseController {
    @Autowired
    private EqpPunishService eqpPunishService;

    /**
     * 提交.
     * @param ids
     * @return
     */
    @PostMapping("/submit")
    public ResultVO submit(@RequestBody IdsDTO ids) {
        EqpPunishQuery query = new EqpPunishQuery();
        query.setIds(ids.getIds());
        int count = eqpPunishService.updateByIds(query);
        return  ResultVO.successResult(count);
    }

    /**
     * 处罚记录.
     * @param query
     * @return
     */
    @GetMapping("/page")
    public ResultVO page(EqpPunishQuery query) {
        query.setEqpType(EqpTypeEnum.typeOf(query.getKey()));

        TableDTO<EqpPunishDTO> tableDTO = eqpPunishService.pageByExample(query);
        Page<EqpPunishDTO> page = new Page<>(query.getOffset(), query.getLimit());
        page.setTotalRow(tableDTO.getTotal());
        page.setList(tableDTO.getRows());
        return ResultVO.successResult(page);
    }
}
