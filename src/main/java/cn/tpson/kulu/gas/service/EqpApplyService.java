package cn.tpson.kulu.gas.service;

import cn.tpson.kulu.gas.domain.EqpApplyDO;
import cn.tpson.kulu.gas.dto.EqpApplyDTO;
import cn.tpson.kulu.gas.query.EqpApplyQuery;

/**
 * Created by Zhangka in 2018/06/14
 */
public interface EqpApplyService extends BaseService<EqpApplyDTO, EqpApplyDO, EqpApplyQuery> {
    EqpApplyDTO getByUid(Integer uid);

    int apply(EqpApplyDTO eqpApplyDTO, Short status);
}
