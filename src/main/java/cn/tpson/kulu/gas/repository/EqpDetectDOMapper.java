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

    /**
     * 根据设备id查询单条检测记录.
     * @param eqpId
     * @return
     */
    EqpDetectDO selectOneByEqpId(Integer eqpId);

    /**
     * 根据设备id查询最新检测结果.
     * @param eqpId
     * @return
     */
    Double selectDetectValueByEqpId(Integer eqpId);

    /**
     * 模糊搜索.
     * @param query.key
     * @return
     */
    List<EqpDetectDO> search(EqpDetectQuery query);

    /**
     * 模糊搜索记录数.
     * @param query.key
     * @return
     */
    Integer countBySearch(EqpDetectQuery query);

    /**
     * 更新log表.
     * @param record
     * @return
     */
    int updateLogByPrimaryKey(EqpDetectDO record);
}
