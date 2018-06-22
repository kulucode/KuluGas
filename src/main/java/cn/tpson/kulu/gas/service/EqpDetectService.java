package cn.tpson.kulu.gas.service;

import cn.tpson.kulu.gas.domain.EqpDetectDO;
import cn.tpson.kulu.gas.dto.EqpDetectDTO;
import cn.tpson.kulu.gas.dto.TableDTO;
import cn.tpson.kulu.gas.query.EqpDetectQuery;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Created by Zhangka in 2018/06/14
 */
public interface EqpDetectService extends BaseService<EqpDetectDTO, EqpDetectDO, EqpDetectQuery> {
    List<EqpDetectDTO> getByEqpId(Integer eqpId);

    /**
     * 添加检测记录.
     * @param eqpDetectDTO
     * @return
     */
    int add(Integer eqpId, String detectValue, String detectNo, Short type);

    /**
     * 模糊搜索.
     * @param query.key
     * @return
     */
    TableDTO<EqpDetectDTO> search(EqpDetectQuery query);

    /**
     * 模糊搜索记录数.
     * @param query.key
     * @return
     */
    Integer countBySearch(EqpDetectQuery query);

    void initEqp(EqpDetectDTO eqpDetectDTO);
}
