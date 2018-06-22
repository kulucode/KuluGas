package cn.tpson.kulu.gas.repository;

import cn.tpson.kulu.gas.domain.EqpApplyDO;
import cn.tpson.kulu.gas.query.EqpApplyQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    /**
     * 插入t_eqp_apply_eqp关系表.
     * @param params
     * @return
     */
    int insertApplyEqp(Map<String, Integer> params);
}
