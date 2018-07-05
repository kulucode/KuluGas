package cn.tpson.kulu.gas.repository;

import cn.tpson.kulu.gas.domain.EqpPunishDO;
import cn.tpson.kulu.gas.query.EqpPunishQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EqpPunishDOMapper extends BaseMapper<EqpPunishQuery, EqpPunishDO> {
    int getPunishNumber();
    Integer getPunishCountByEqpNo(EqpPunishQuery query);
}