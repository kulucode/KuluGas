package cn.tpson.kulu.gas.service;

import cn.tpson.kulu.gas.domain.SysBlockChainDO;
import cn.tpson.kulu.gas.dto.SysBlockChainDTO;
import cn.tpson.kulu.gas.query.SysBlockChainQuery;

/**
 * Created by Zhangka in 2018/06/27
 */
public interface SysBlockChainService extends BaseService<SysBlockChainDTO, SysBlockChainDO, SysBlockChainQuery> {
    SysBlockChainDTO selectOneByExample(SysBlockChainQuery query);
}
