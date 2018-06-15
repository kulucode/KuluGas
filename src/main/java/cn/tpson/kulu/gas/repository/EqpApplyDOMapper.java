package cn.tpson.kulu.gas.repository;

import cn.tpson.kulu.gas.domain.EqpApplyDO;
import cn.tpson.kulu.gas.query.EqpApplyQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zhangka in 2018/06/14
 */
@Repository
public interface EqpApplyDOMapper extends BaseMapper<EqpApplyQuery, EqpApplyDO> {
    /**
     * 根据uid查询申请记录.
     * @param uid
     * @return
     */
    List<EqpApplyDO> selectByUid(Integer uid);
}
