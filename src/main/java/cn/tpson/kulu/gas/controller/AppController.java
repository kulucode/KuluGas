package cn.tpson.kulu.gas.controller;

import cn.tpson.kulu.gas.constant.ErrorCodeEnum;
import cn.tpson.kulu.gas.dto.EqpDTO;
import cn.tpson.kulu.gas.dto.EqpDetectDTO;
import cn.tpson.kulu.gas.dto.EqpPunishDTO;
import cn.tpson.kulu.gas.service.EqpDetectService;
import cn.tpson.kulu.gas.service.EqpPunishService;
import cn.tpson.kulu.gas.service.EqpService;
import cn.tpson.kulu.gas.util.CheckUtils;
import cn.tpson.kulu.gas.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * APP端获取检测数据.
 * Created by Zhangka in 2018/06/29
 */
@RestController
@RequestMapping("/app")
public class AppController extends BaseController {
    @Autowired
    private EqpService eqpService;
    @Autowired
    private EqpDetectService eqpDetectService;
    @Autowired
    private EqpPunishService eqpPunishService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private RedisTemplate<String, EqpDetectDTO> detectRedisTemplate;

    /**
     * 触发检测事件.
     * @param eqpId
     * @return
     */
    @GetMapping("/detecting")
    public ResultVO detecting(Integer eqpId) {
        CheckUtils.checkNull(eqpId, ErrorCodeEnum.PARAM_ERROR);
        EqpDTO eqpDTO = eqpService.getById(eqpId);
        CheckUtils.checkNull(eqpDTO, ErrorCodeEnum.EQP_NOT_FOUND);

        /*******************************************************************************************/
        // 模拟产生数据
        EqpDetectDTO eqpDetectDTO = new EqpDetectDTO();
        eqpDetectDTO.setEqpId(eqpDTO.getId());
        eqpDetectDTO.setDetectNo("7777777");
        eqpDetectDTO.setDetectValue(new BigDecimal("0.59"));
        detectRedisTemplate.opsForValue().set(eqpDTO.getEqpNo(), eqpDetectDTO, 20, TimeUnit.MINUTES);
        /*******************************************************************************************/

        Long size = redisTemplate.opsForList().leftPush("eqpNo", eqpDTO.getEqpNo());
        return size > 0 ? ResultVO.successResult(size)
                        : ResultVO.failResult(ErrorCodeEnum.SYS_OPERATE_FAIL);
    }

    /**
     * 获取检测结果.
     * @param eqpId
     * @return
     */
    @GetMapping("/detected/val")
    public ResultVO detectVal(Integer eqpId) {
        CheckUtils.checkNull(eqpId, ErrorCodeEnum.PARAM_ERROR);
        EqpDTO eqpDTO = eqpService.getById(eqpId);
        CheckUtils.checkNull(eqpDTO, ErrorCodeEnum.EQP_NOT_FOUND);

        EqpDetectDTO eqpDetectDTO = detectRedisTemplate.opsForValue().get(eqpDTO.getEqpNo());
        return eqpDetectDTO == null ? ResultVO.failResult(ErrorCodeEnum.EQP_DETECT_NOT_FOUND)
                                    : ResultVO.successResult(eqpDetectDTO);
    }

    /**
     * 提交检测结果.
     *
     * @param eqpId，设备编号
     * @param detectNo, 检测设备编号
     * @param val，检测值
     * @param lat，纬度
     * @param lon，经度
     * @return
     */
    @PutMapping("/submit")
    public ResultVO submit(Integer eqpId, String detectNo, String val, Double lat, Double lon) {
        CheckUtils.checkNull(eqpId, ErrorCodeEnum.PARAM_ERROR);
        CheckUtils.checkBlank(detectNo, ErrorCodeEnum.PARAM_ERROR);
        CheckUtils.checkNumber(val, ErrorCodeEnum.PARAM_ERROR);
        EqpDTO eqpDTO = eqpService.getById(eqpId);
        CheckUtils.checkNull(eqpDTO, ErrorCodeEnum.EQP_NOT_FOUND);

        int id = eqpDetectService.add(eqpId, val, detectNo, null, lat, lon);
        return ResultVO.successResult(id);
    }

    /**
     * 处罚.
     * @return
     */

    /**
     * 处罚.
     * @param eqpId
     * @param amount
     * @param detectNo
     * @param repairDate
     * @param lat
     * @param lon
     * @return
     */
    @PutMapping("/punish")
    public ResultVO punish(Integer eqpId, String amount, String detectNo,
                           @DateTimeFormat(pattern="yyyy-MM-dd") Date repairDate,
                           Double lat, Double lon, Double detectValue, Short type, String bsName) {
        EqpPunishDTO eqpPunishDTO = eqpPunishService.punish(eqpId, amount, detectNo, repairDate, lat, lon, detectValue, type, bsName);
        return ResultVO.successResult(eqpPunishDTO);
    }
}
