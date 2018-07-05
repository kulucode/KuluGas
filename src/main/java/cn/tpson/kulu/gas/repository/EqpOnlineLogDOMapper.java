package cn.tpson.kulu.gas.repository;

import cn.tpson.kulu.gas.domain.EqpOnlineLogDO;
import cn.tpson.kulu.gas.query.EqpOnlineLogQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface EqpOnlineLogDOMapper extends BaseMapper<EqpOnlineLogQuery, EqpOnlineLogDO> {
    Integer getWorkingHoursByEqpId(Integer eqpId);

    Integer getWorkingHoursByEqpIdAndGmtCreate(@Param("eqpId") Integer eqpId, @Param("gmtCreate") Instant gmtCreate);
}