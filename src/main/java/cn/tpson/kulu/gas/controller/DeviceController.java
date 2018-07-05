package cn.tpson.kulu.gas.controller;

import cn.tpson.kulu.gas.constant.ErrorCodeEnum;
import cn.tpson.kulu.gas.dto.*;
import cn.tpson.kulu.gas.service.EqpDetectService;
import cn.tpson.kulu.gas.service.EqpLocationService;
import cn.tpson.kulu.gas.service.EqpOnlineLogService;
import cn.tpson.kulu.gas.service.EqpService;
import cn.tpson.kulu.gas.util.CheckUtils;
import cn.tpson.kulu.gas.vo.ResultVO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 提供给思拓的接口.
 * Created by Zhangka in 2018/06/29
 */
@RestController
@RequestMapping("/e")
public class DeviceController extends BaseController {
    @Autowired
    private EqpService eqpService;
    @Autowired
    private EqpOnlineLogService eqpOnlineLogService;
    @Autowired
    private EqpLocationService eqpLocationService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private RedisTemplate<String, EqpDetectDTO> detectRedisTemplate;

    /**
     * 设备上线通知.
     *
     * @param eqpNo，设备编号
     * @param lat，纬度
     * @param lon，经度
     * @return
     */
    @GetMapping("/online")
    public ResultVO online(String eqpNo, String lat, String lon) {
        return log(eqpNo, lat, lon, Boolean.TRUE);
    }

    /**
     * 设备下线通知.
     *
     * @param eqpNo，设备编号
     * @param lat，纬度
     * @param lon，经度
     * @return
     */
    @GetMapping("/offline")
    public ResultVO offline(String eqpNo, String lat, String lon) {
        return log(eqpNo, lat, lon, Boolean.FALSE);
    }

    /**
     * 设备实时定位通知.
     *
     * @param eqpNo，设备编号
     * @param lat，纬度
     * @param lon，经度
     * @return
     */
    @GetMapping("/location")
    public ResultVO location(String eqpNo, Double lat, Double lon) {
        CheckUtils.checkBlank(eqpNo, ErrorCodeEnum.PARAM_ERROR);
        EqpDTO eqpDTO = eqpService.getByEqpNo(eqpNo);
        CheckUtils.checkNull(eqpDTO, ErrorCodeEnum.EQP_NOT_FOUND);

        return ResultVO.successResult(eqpLocationService.addLocation(eqpDTO.getId(), lat, lon));
    }

    /**
     * 设备检测值通知.
     *
     * @param eqpNo，设备编号
     * @param detectNo,  检测设备编号
     * @param val，检测值
     * @return
     */
    @GetMapping("/detect")
    public ResultVO detect(String eqpNo, String detectNo, String val) {
        CheckUtils.checkBlank(eqpNo, ErrorCodeEnum.PARAM_ERROR);
        CheckUtils.checkBlank(detectNo, ErrorCodeEnum.PARAM_ERROR);
        EqpDTO eqpDTO = eqpService.getByEqpNo(eqpNo);
        CheckUtils.checkNull(eqpDTO, ErrorCodeEnum.EQP_NOT_FOUND);
        CheckUtils.checkTrue((NumberUtils.isParsable(val)), ErrorCodeEnum.PARAM_ERROR);

        EqpDetectDTO eqpDetectDTO = new EqpDetectDTO();
        eqpDetectDTO.setEqpId(eqpDTO.getId());
        eqpDetectDTO.setDetectNo(detectNo);
        eqpDetectDTO.setDetectValue(new BigDecimal(val));
        detectRedisTemplate.opsForValue().set(eqpNo, eqpDetectDTO, 20, TimeUnit.MINUTES);
        return ResultVO.successResult();
    }


    /**
     * 获取待上报设备.
     *
     * @return
     */
    @GetMapping("/s")
    public ResultVO s() {
        List<String> list = redisTemplate.opsForList().range("eqpNo", 0, -1);
        JSONArray array = new JSONArray(list.size());
        for (String s : list) {
            JSONObject object = new JSONObject();
            object.put("eqpNo", s);
            array.add(object);
        }
        return ResultVO.successResult(array);
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////
    protected ResultVO log(String eqpNo, String lat, String lon, boolean online) {
        CheckUtils.checkBlank(eqpNo, ErrorCodeEnum.PARAM_ERROR);
        EqpDTO eqpDTO = eqpService.getByEqpNo(eqpNo);
        CheckUtils.checkNull(eqpDTO, ErrorCodeEnum.EQP_NOT_FOUND);
        CheckUtils.checkTrue((NumberUtils.isParsable(lat) || NumberUtils.isParsable(lon)), ErrorCodeEnum.PARAM_ERROR);

        Instant now = Instant.now();
        EqpOnlineLogDTO dto = new EqpOnlineLogDTO(null, Boolean.FALSE, now, now);
        dto.setEqpId(eqpDTO.getId());
        dto.setOnline(online);
        dto.setLat(Double.valueOf(lat));
        dto.setLon(Double.valueOf(lon));
        int id = eqpOnlineLogService.insert(dto);
        return ResultVO.successResult(id);
    }
}
