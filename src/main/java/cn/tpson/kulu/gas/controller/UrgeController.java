package cn.tpson.kulu.gas.controller;

import cn.tpson.kulu.gas.constant.EqpTypeEnum;
import cn.tpson.kulu.gas.dto.EqpDetectUrgeDTO;
import cn.tpson.kulu.gas.dto.IdsDTO;
import cn.tpson.kulu.gas.dto.Page;
import cn.tpson.kulu.gas.dto.TableDTO;
import cn.tpson.kulu.gas.query.EqpDetectUrgeQuery;
import cn.tpson.kulu.gas.query.EqpPunishQuery;
import cn.tpson.kulu.gas.service.EqpDetectUrgeService;
import cn.tpson.kulu.gas.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Zhangka in 2018/07/04
 */
@RestController
@RequestMapping("/urge")
public class UrgeController extends BaseController {
    @Autowired
    private EqpDetectUrgeService eqpDetectUrgeService;

    /**
     * 提交.
     *
     * @param ids
     * @return
     */
    @PostMapping("/submit")
    public ResultVO submit(@RequestBody IdsDTO ids) {
        EqpDetectUrgeQuery query = new EqpDetectUrgeQuery();
        query.setIds(ids.getIds());
        query.setStatus(EqpDetectUrgeDTO.STATUS_SUBMITTED);
        int count = eqpDetectUrgeService.updateByIds(query);
        return ResultVO.successResult(count);
    }

    /**
     * 督促检测记录.
     *
     * @param query
     * @return
     */
    @GetMapping("/page")
    public ResultVO page(EqpDetectUrgeQuery query) {
        query.setEqpType(EqpTypeEnum.typeOf(query.getKey()));

        TableDTO<EqpDetectUrgeDTO> tableDTO = eqpDetectUrgeService.pageByExample(query);
        Page<EqpDetectUrgeDTO> page = new Page<>(query.getOffset(), query.getLimit());
        page.setTotalRow(tableDTO.getTotal());
        page.setList(tableDTO.getRows());
        return ResultVO.successResult(page);
    }
}
