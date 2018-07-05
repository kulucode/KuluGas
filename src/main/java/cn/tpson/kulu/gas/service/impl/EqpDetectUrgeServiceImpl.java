package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.domain.EqpDetectUrgeDO;
import cn.tpson.kulu.gas.dto.EqpDetectUrgeDTO;
import cn.tpson.kulu.gas.query.EqpDetectUrgeQuery;
import cn.tpson.kulu.gas.repository.BaseMapper;
import cn.tpson.kulu.gas.repository.EqpDetectUrgeDOMapper;
import cn.tpson.kulu.gas.service.EqpDetectUrgeService;
import cn.tpson.kulu.gas.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Zhangka in 2018/07/02
 */
@Service
@Transactional(readOnly = true)
public class EqpDetectUrgeServiceImpl extends BaseServiceImpl<EqpDetectUrgeDTO, EqpDetectUrgeDO, EqpDetectUrgeQuery> implements EqpDetectUrgeService {
    @Autowired
    private EqpDetectUrgeDOMapper eqpDetectUrgeDOMapper;

    @Override
    public BaseMapper<EqpDetectUrgeQuery, EqpDetectUrgeDO> mapper() {
        return eqpDetectUrgeDOMapper;
    }

    @Override
    public EqpDetectUrgeDTO getByYearAndEqpId(Integer year, Integer eqpId) {
        return BeanUtils.copyProperties(EqpDetectUrgeDTO.class, eqpDetectUrgeDOMapper.selectByYearAndEqpId(year, eqpId));
    }
}
