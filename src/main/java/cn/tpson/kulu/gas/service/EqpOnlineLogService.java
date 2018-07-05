package cn.tpson.kulu.gas.service;

import cn.tpson.kulu.gas.domain.EqpOnlineLogDO;
import cn.tpson.kulu.gas.dto.EqpOnlineLogDTO;
import cn.tpson.kulu.gas.query.EqpOnlineLogQuery;

import java.time.Instant;

/**
 * Created by Zhangka in 2018/07/02
 */
public interface EqpOnlineLogService extends BaseService<EqpOnlineLogDTO, EqpOnlineLogDO, EqpOnlineLogQuery> {
    Integer getWorkingHoursByEqpId(Integer eqpId);

    Integer getWorkingHoursByEqpIdAndGmtCreate(Integer eqpId, Instant gmtCreate);
}
