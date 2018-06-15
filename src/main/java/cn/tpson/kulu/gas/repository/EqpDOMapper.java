package cn.tpson.kulu.gas.repository;

import cn.tpson.kulu.gas.domain.EqpDO;
import cn.tpson.kulu.gas.query.EqpQuery;
import org.springframework.stereotype.Repository;

/**
 * Created by Zhangka in 2018/06/14
 */
@Repository
public interface EqpDOMapper extends BaseMapper<EqpQuery, EqpDO> {
    /**
     * 根据设备标识号查询.
     * @param eqpNo
     * @return
     */
    EqpDO selectByEqpNo(String eqpNo);
}
