package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.domain.EqpDetectDO;
import cn.tpson.kulu.gas.dto.EqpDetectDTO;
import cn.tpson.kulu.gas.query.EqpDetectQuery;
import cn.tpson.kulu.gas.repository.BaseMapper;
import cn.tpson.kulu.gas.repository.EqpDetectDOMapper;
import cn.tpson.kulu.gas.service.EqpDetectService;
import cn.tpson.kulu.gas.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Zhangka in 2018/06/15
 */
@Service
@Transactional(readOnly = true)
public class EqpDetectServiceImpl extends BaseServiceImpl<EqpDetectDTO, EqpDetectDO, EqpDetectQuery> implements EqpDetectService {
    @Autowired
    private EqpDetectDOMapper eqpDetectDOMapper;

    @Override
    public EqpDetectDTO getByEqpId(Integer eqpId) {
        return BeanUtils.copyProperties(EqpDetectDTO.class, eqpDetectDOMapper.selectByEqpId(eqpId));
    }

    @Override
    public BaseMapper<EqpDetectQuery, EqpDetectDO> mapper() {
        return eqpDetectDOMapper;
    }
}
