package cn.tpson.kulu.gas.repository;

import cn.tpson.kulu.gas.domain.EqpDetectLogDO;
import cn.tpson.kulu.gas.query.EqpDetectLogQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zhangka in 2018/06/14
 */
@Repository
public interface EqpDetectLogDOMapper extends BaseMapper<EqpDetectLogQuery, EqpDetectLogDO> {
    /**
     * 根据设备id查询检测记录.
     * @param eqpId
     * @return
     */
    List<EqpDetectLogDO> selectByEqpId(Integer eqpId);

    /**
     * 根据设备id查询单挑检测记录.
     * @param eqpId
     * @return
     */
    EqpDetectLogDO selectOneByEqpId(Integer eqpId);

    /**
     * 模糊搜索.
     * @param query.key
     * @return
     */
    List<EqpDetectLogDO> search(EqpDetectLogQuery query);

    /**
     * 模糊搜索记录数.
     * @param query.key
     * @return
     */
    Integer countBySearch(EqpDetectLogQuery query);
}
