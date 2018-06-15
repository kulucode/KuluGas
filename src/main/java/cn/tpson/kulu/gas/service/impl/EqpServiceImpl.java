package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.domain.EqpDO;
import cn.tpson.kulu.gas.dto.EqpDTO;
import cn.tpson.kulu.gas.query.EqpQuery;
import cn.tpson.kulu.gas.repository.BaseMapper;
import cn.tpson.kulu.gas.repository.EqpDOMapper;
import cn.tpson.kulu.gas.service.EqpService;
import cn.tpson.kulu.gas.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Zhangka in 2018/06/14
 */
@Service
@Transactional(readOnly = true)
public class EqpServiceImpl extends BaseServiceImpl<EqpDTO, EqpDO, EqpQuery> implements EqpService {
    @Autowired
    private EqpDOMapper eqpDOMapper;

    @Override
    public EqpDTO getByEqpNo(String eqpNo) {
        return BeanUtils.copyProperties(EqpDTO.class, eqpDOMapper.selectByEqpNo(eqpNo));
    }

    @Override
    public BaseMapper<EqpQuery, EqpDO> mapper() {
        return eqpDOMapper;
    }
}
