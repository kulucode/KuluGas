package cn.tpson.kulu.gas.repository;

import cn.tpson.kulu.gas.domain.EqpLocationCurrentDO;
import cn.tpson.kulu.gas.domain.EqpLocationDO;
import cn.tpson.kulu.gas.query.EqpLocationCurrentQuery;
import cn.tpson.kulu.gas.query.EqpLocationQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EqpLocationCurrentDOMapper extends BaseMapper<EqpLocationCurrentQuery, EqpLocationCurrentDO> {
    EqpLocationCurrentDO selectByEqpId(Integer eqpId);

    List<EqpLocationCurrentDO> selectByUid(Integer uid);
}