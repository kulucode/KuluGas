package cn.tpson.kulu.gas.controller;

import cn.tpson.kulu.gas.dto.EqpApplyDTO;
import cn.tpson.kulu.gas.dto.EqpDTO;
import cn.tpson.kulu.gas.dto.SysUserDTO;
import cn.tpson.kulu.gas.service.EqpApplyService;
import cn.tpson.kulu.gas.service.EqpService;
import cn.tpson.kulu.gas.vo.ResultVO;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhangka in 2018/06/14
 */
@RestController
@RequestMapping("/eqp")
public class EqpController extends BaseController {
    @Autowired
    private EqpApplyService eqpApplyService;
    @Autowired
    private EqpService eqpService;

    /**
     * 装机/拆机申请.
     * @param eqpApplyDTO
     * @return
     */
    @PostMapping("/apply")
    public ResultVO apply(@RequestBody EqpApplyDTO eqpApplyDTO) {
        if (eqpApplyDTO.getEqps() == null || eqpApplyDTO.getEqps().size() == 0) {
            return ResultVO.failResult("请添加设备.");
        }

        Short type = eqpApplyDTO.getType();
        if (type == null || (type != EqpApplyDTO.TYPE_INSTALL && type != EqpApplyDTO.TYPE_UNINSTALL)) {
            return ResultVO.failResult("类型错误.");
        }

        // 设置参数
        SysUserDTO sysUserDTO = getUser();
        Instant now = Instant.now();
        eqpApplyDTO.setUid(sysUserDTO.getId());
        eqpApplyDTO.setLocation(JSON.toJSONString(eqpApplyDTO.getGps()));
        eqpApplyDTO.setGmtCreate(now);
        eqpApplyDTO.setDeleted(false);
        eqpApplyDTO.setStatus(EqpApplyDTO.STATUS_WAITING);
        eqpApplyDTO.getEqps().forEach(eqpDTO -> {
            eqpDTO.setGmtCreate(now);
            eqpDTO.setDeleted(false);
        });
        eqpApplyService.insert(eqpApplyDTO);

        return ResultVO.successResult();
    }

    /**
     * 设备详情.
     * @param eqpId
     * @return
     */
    @GetMapping("/detail")
    public ResultVO detail(Integer eqpId) {
        return ResultVO.successResult(eqpService.getById(eqpId));
    }
}
