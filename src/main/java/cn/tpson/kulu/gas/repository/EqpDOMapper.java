package cn.tpson.kulu.gas.repository;

import cn.tpson.kulu.gas.domain.EqpDO;
import cn.tpson.kulu.gas.query.EqpQuery;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 获取所有设备id.
     * @return
     */
    @Select("select id from t_eqp where is_deleted = false")
    List<Integer> getAllId();
}
