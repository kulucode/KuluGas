package cn.tpson.kulu.gas.repository;

import cn.tpson.kulu.gas.domain.EqpDetectUrgeDO;
import cn.tpson.kulu.gas.query.EqpDetectUrgeQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EqpDetectUrgeDOMapper extends BaseMapper<EqpDetectUrgeQuery, EqpDetectUrgeDO> {
    EqpDetectUrgeDO selectByYearAndEqpId(@Param("year") Integer year, @Param("eqpId") Integer eqpId);
}