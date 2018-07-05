package cn.tpson.kulu.gas.service;

import cn.tpson.kulu.gas.domain.EqpLocationCurrentDO;
import cn.tpson.kulu.gas.domain.EqpLocationDO;
import cn.tpson.kulu.gas.dto.EqpLocationCurrentDTO;
import cn.tpson.kulu.gas.dto.EqpLocationDTO;
import cn.tpson.kulu.gas.query.EqpLocationCurrentQuery;
import cn.tpson.kulu.gas.query.EqpLocationQuery;

import java.util.List;

/**
 * Created by Zhangka in 2018/07/02
 */
public interface EqpLocationCurrentService extends BaseService<EqpLocationCurrentDTO, EqpLocationCurrentDO, EqpLocationCurrentQuery> {
    EqpLocationCurrentDTO getByEqpId(Integer eqpId);

    List<EqpLocationCurrentDTO> getByUid(Integer uid);
}
