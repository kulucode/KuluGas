package cn.tpson.kulu.gas.repository;

import cn.tpson.kulu.gas.domain.EqpLocationDO;
import cn.tpson.kulu.gas.dto.EqpLocationDTO;
import cn.tpson.kulu.gas.query.EqpLocationQuery;
import org.springframework.stereotype.Repository;

@Repository
public interface EqpLocationDOMapper extends BaseMapper<EqpLocationQuery, EqpLocationDO> {
    int addLocation(EqpLocationDTO location);

    Integer getId(EqpLocationQuery query);
}