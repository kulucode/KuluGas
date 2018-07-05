package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.domain.SysBlockChainDO;
import cn.tpson.kulu.gas.dto.SysBlockChainDTO;
import cn.tpson.kulu.gas.query.SysBlockChainQuery;
import cn.tpson.kulu.gas.repository.BaseMapper;
import cn.tpson.kulu.gas.repository.SysBlockChainDOMapper;
import cn.tpson.kulu.gas.service.SysBlockChainService;
import cn.tpson.kulu.gas.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Zhangka in 2018/06/27
 */
@Service
@Transactional(readOnly = true)
public class SysBlockChainServiceImpl extends BaseServiceImpl<SysBlockChainDTO, SysBlockChainDO, SysBlockChainQuery> implements SysBlockChainService {
    @Autowired
    private SysBlockChainDOMapper sysBlockChainDOMapper;

    @Override
    public SysBlockChainDTO selectOneByExample(SysBlockChainQuery query) {
        return BeanUtils.copyProperties(SysBlockChainDTO.class, sysBlockChainDOMapper.selectOneByExample(query));
    }

    @Override
    public BaseMapper<SysBlockChainQuery, SysBlockChainDO> mapper() {
        return sysBlockChainDOMapper;
    }
}
