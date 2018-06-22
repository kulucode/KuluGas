package cn.tpson.kulu.gas.service;

import cn.tpson.kulu.gas.domain.EqpDetectLogDO;
import cn.tpson.kulu.gas.dto.EqpDetectLogDTO;
import cn.tpson.kulu.gas.dto.TableDTO;
import cn.tpson.kulu.gas.query.EqpDetectLogQuery;

import java.util.List;

/**
 * Created by Zhangka in 2018/06/14
 */
public interface EqpDetectLogService extends BaseService<EqpDetectLogDTO, EqpDetectLogDO, EqpDetectLogQuery> {
    List<EqpDetectLogDTO> getByEqpId(Integer eqpId);

    /**
     * 模糊搜索.
     * @param query.key
     * @return
     */
    TableDTO<EqpDetectLogDTO> search(EqpDetectLogQuery query);

    /**
     * 模糊搜索记录数.
     * @param query.key
     * @return
     */
    Integer countBySearch(EqpDetectLogQuery query);

    void initEqp(EqpDetectLogDTO eqpDetectDTO);
}
