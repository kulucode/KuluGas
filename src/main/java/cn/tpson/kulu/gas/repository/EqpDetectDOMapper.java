package cn.tpson.kulu.gas.repository;

import cn.tpson.kulu.gas.domain.EqpDetectDO;
import cn.tpson.kulu.gas.query.EqpDetectQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zhangka in 2018/06/14
 */
@Repository
public interface EqpDetectDOMapper extends BaseMapper<EqpDetectQuery, EqpDetectDO> {
    /**
     * 根据设备id查询检测记录.
     * @param eqpId
     * @return
     */
    List<EqpDetectDO> selectByEqpId(Integer eqpId);
}
