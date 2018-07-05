package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.domain.EqpLocationCurrentDO;
import cn.tpson.kulu.gas.domain.EqpLocationDO;
import cn.tpson.kulu.gas.dto.EqpLocationCurrentDTO;
import cn.tpson.kulu.gas.dto.EqpLocationDTO;
import cn.tpson.kulu.gas.query.EqpLocationCurrentQuery;
import cn.tpson.kulu.gas.query.EqpLocationQuery;
import cn.tpson.kulu.gas.repository.BaseMapper;
import cn.tpson.kulu.gas.repository.EqpLocationCurrentDOMapper;
import cn.tpson.kulu.gas.repository.EqpLocationDOMapper;
import cn.tpson.kulu.gas.service.EqpLocationCurrentService;
import cn.tpson.kulu.gas.service.EqpLocationService;
import cn.tpson.kulu.gas.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Zhangka in 2018/07/02
 */
@Service
@Transactional(readOnly = true)
public class EqpLocationCurrentServiceImpl extends BaseServiceImpl<EqpLocationCurrentDTO, EqpLocationCurrentDO, EqpLocationCurrentQuery> implements EqpLocationCurrentService {
    @Autowired
    private EqpLocationCurrentDOMapper eqpLocationCurrentDOMapper;

    @Override
    public BaseMapper<EqpLocationCurrentQuery, EqpLocationCurrentDO> mapper() {
        return eqpLocationCurrentDOMapper;
    }

    @Override
    public EqpLocationCurrentDTO getByEqpId(Integer eqpId) {
        return BeanUtils.copyProperties(EqpLocationCurrentDTO.class, eqpLocationCurrentDOMapper.selectByEqpId(eqpId));
    }

    @Override
    public List<EqpLocationCurrentDTO> getByUid(Integer uid) {
        return BeanUtils.copyPropertiesForList(EqpLocationCurrentDTO.class, eqpLocationCurrentDOMapper.selectByUid(uid));
    }
}
