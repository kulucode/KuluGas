package cn.tpson.kulu.gas.service;

import cn.tpson.kulu.gas.domain.EqpDO;
import cn.tpson.kulu.gas.dto.EqpDTO;
import cn.tpson.kulu.gas.query.EqpQuery;

/**
 * Created by Zhangka in 2018/06/14
 */
public interface EqpService extends BaseService<EqpDTO, EqpDO, EqpQuery> {
    EqpDTO getByEqpNo(String eqpNo);
}
