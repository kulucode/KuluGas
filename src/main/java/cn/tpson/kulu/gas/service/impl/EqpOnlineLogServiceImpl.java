package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.domain.EqpOnlineLogDO;
import cn.tpson.kulu.gas.dto.EqpOnlineLogDTO;
import cn.tpson.kulu.gas.query.EqpOnlineLogQuery;
import cn.tpson.kulu.gas.repository.BaseMapper;
import cn.tpson.kulu.gas.repository.EqpOnlineLogDOMapper;
import cn.tpson.kulu.gas.service.EqpOnlineLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

/**
 * Created by Zhangka in 2018/07/02
 */
@Service
@Transactional(readOnly = true)
public class EqpOnlineLogServiceImpl extends BaseServiceImpl<EqpOnlineLogDTO, EqpOnlineLogDO, EqpOnlineLogQuery> implements EqpOnlineLogService {
    @Autowired
    private EqpOnlineLogDOMapper eqpOnlineLogDOMapper;

    @Override
    public Integer getWorkingHoursByEqpId(Integer eqpId) {
        return eqpOnlineLogDOMapper.getWorkingHoursByEqpId(eqpId);
    }

    @Override
    public Integer getWorkingHoursByEqpIdAndGmtCreate(Integer eqpId, Instant gmtCreate) {
        return eqpOnlineLogDOMapper.getWorkingHoursByEqpIdAndGmtCreate(eqpId, gmtCreate);
    }

    @Override
    public BaseMapper<EqpOnlineLogQuery, EqpOnlineLogDO> mapper() {
        return eqpOnlineLogDOMapper;
    }
}
