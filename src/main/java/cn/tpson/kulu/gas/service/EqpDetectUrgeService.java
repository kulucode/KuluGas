package cn.tpson.kulu.gas.service;

import cn.tpson.kulu.gas.domain.EqpDetectUrgeDO;
import cn.tpson.kulu.gas.domain.EqpPunishDO;
import cn.tpson.kulu.gas.dto.EqpDetectUrgeDTO;
import cn.tpson.kulu.gas.dto.EqpPunishDTO;
import cn.tpson.kulu.gas.query.EqpDetectUrgeQuery;
import cn.tpson.kulu.gas.query.EqpPunishQuery;

import java.util.Date;

/**
 * Created by Zhangka in 2018/07/02
 */
public interface EqpDetectUrgeService extends BaseService<EqpDetectUrgeDTO, EqpDetectUrgeDO, EqpDetectUrgeQuery> {
    EqpDetectUrgeDTO getByYearAndEqpId(Integer year, Integer eqpId);
}
