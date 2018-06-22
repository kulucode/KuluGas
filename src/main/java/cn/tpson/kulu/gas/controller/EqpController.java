package cn.tpson.kulu.gas.controller;

import cn.tpson.kulu.gas.dto.EqpApplyDTO;
import cn.tpson.kulu.gas.dto.SysUserDTO;
import cn.tpson.kulu.gas.query.EqpQuery;
import cn.tpson.kulu.gas.service.EqpApplyService;
import cn.tpson.kulu.gas.service.EqpService;
import cn.tpson.kulu.gas.vo.ResultVO;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

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
     * 装机申请.
     * @param eqpApplyDTO
     * @return
     */
    @PostMapping(value = "/install")
    public ResultVO install(@RequestBody EqpApplyDTO eqpApplyDTO) {
        eqpApplyDTO.setType(EqpApplyDTO.TYPE_INSTALL);
        return apply(eqpApplyDTO);
    }

    /**
     * 拆机申请.
     * @param eqpApplyDTO
     * @return
     */
    @PostMapping(value = "/uninstall")
    public ResultVO uninstall(@RequestBody EqpApplyDTO eqpApplyDTO) {
        eqpApplyDTO.setType(EqpApplyDTO.TYPE_UNINSTALL);
        return apply(eqpApplyDTO);
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

    /**
     * 获取当前用户设备列表.
     * @return
     */
    @GetMapping("/list")
    public ResultVO list() {
        SysUserDTO sysUserDTO = getUser();
        if (sysUserDTO.getType() != SysUserDTO.TYPE_PER && sysUserDTO.getType() != SysUserDTO.TYPE_BUI) {
            return ResultVO.failResult("无权操作.");
        }

        EqpQuery query = new EqpQuery();
        query.setUid(getUid());
        return ResultVO.successResult(eqpService.listByExample(query));
    }

    /**
     * 装机/拆机申请.
     * @param eqpApplyDTO
     * @return
     */
    protected ResultVO apply(EqpApplyDTO eqpApplyDTO) {
        if (eqpApplyDTO.getEqps() == null || eqpApplyDTO.getEqps().size() == 0) {
            return ResultVO.failResult("请添加设备.");
        }

        // 设置参数
        SysUserDTO sysUserDTO = getUser();
        if (sysUserDTO.getType() == SysUserDTO.TYPE_PER) {
            eqpApplyDTO.getEqps().forEach(eqpDTO -> eqpDTO.setOwnerName(sysUserDTO.getRealname()));
        }

        Instant now = Instant.now();
        eqpApplyDTO.setUid(sysUserDTO.getId());
        eqpApplyDTO.setLocation(JSON.toJSONString(eqpApplyDTO.getGps()));
        eqpApplyDTO.setGmtCreate(now);
        eqpApplyDTO.setDeleted(Boolean.FALSE);
        eqpApplyDTO.setStatus(EqpApplyDTO.STATUS_WAITING);
        eqpApplyService.insert(eqpApplyDTO);

        return ResultVO.successResult();
    }
}
