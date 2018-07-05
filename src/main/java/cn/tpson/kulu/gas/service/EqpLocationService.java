package cn.tpson.kulu.gas.service;

import cn.tpson.kulu.gas.domain.EqpLocationDO;
import cn.tpson.kulu.gas.dto.EqpDTO;
import cn.tpson.kulu.gas.dto.EqpLocationDTO;
import cn.tpson.kulu.gas.query.EqpLocationQuery;

/**
 * Created by Zhangka in 2018/07/02
 */
public interface EqpLocationService extends BaseService<EqpLocationDTO, EqpLocationDO, EqpLocationQuery> {
    int addLocation(Integer eqpId, Double lat, Double lon);

    Integer getId(Integer eqpId, String queryDay);
}
