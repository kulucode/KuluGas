package cn.tpson.kulu.gas.service;

import cn.tpson.kulu.gas.domain.EqpDetectDO;
import cn.tpson.kulu.gas.dto.EqpDetectDTO;
import cn.tpson.kulu.gas.query.EqpDetectQuery;

/**
 * Created by Zhangka in 2018/06/14
 */
public interface EqpDetectService extends BaseService<EqpDetectDTO, EqpDetectDO, EqpDetectQuery> {
    EqpDetectDTO getByEqpId(Integer eqpId);
}
