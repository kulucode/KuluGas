package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.domain.EqpApplyDO;
import cn.tpson.kulu.gas.dto.EqpApplyDTO;
import cn.tpson.kulu.gas.query.EqpApplyQuery;
import cn.tpson.kulu.gas.repository.BaseMapper;
import cn.tpson.kulu.gas.repository.EqpApplyDOMapper;
import cn.tpson.kulu.gas.service.EqpApplyService;
import cn.tpson.kulu.gas.service.EqpService;
import cn.tpson.kulu.gas.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zhangka in 2018/06/15
 */
@Service
@Transactional(readOnly = true)
public class EqpApplyServiceImpl extends BaseServiceImpl<EqpApplyDTO, EqpApplyDO, EqpApplyQuery> implements EqpApplyService {
    @Autowired
    private EqpApplyDOMapper eqpApplyDOMapper;
    @Autowired
    private EqpService eqpService;

    @Override
    public EqpApplyDTO getByUid(Integer uid) {
        return BeanUtils.copyProperties(EqpApplyDTO.class, eqpApplyDOMapper.selectByUid(uid));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insert(EqpApplyDTO record) {
        int id = super.insert(record);
        if (record.getEqps() != null) {
            if (record.getType() == EqpApplyDTO.TYPE_INSTALL) {
                record.getEqps().forEach(eqpDTO -> {
                    eqpDTO.setUid(record.getUid());
                    eqpDTO.setGmtCreate(record.getGmtCreate());
                    eqpDTO.setDeleted(Boolean.FALSE);
                    eqpService.insert(eqpDTO);
                });
            } else if (record.getType() == EqpApplyDTO.TYPE_UNINSTALL) {
                record.getEqps().forEach(eqpDTO -> {
                    if (eqpDTO.getId() != null) {
                        Map<String, Integer> params = new HashMap<>(2);
                        params.put("applyId", id);
                        params.put("eqpId", eqpDTO.getId());
                        eqpApplyDOMapper.insertApplyEqp(params);
                    }
                });
            }
        }

        return id;
    }

    @Override
    public BaseMapper<EqpApplyQuery, EqpApplyDO> mapper() {
        return eqpApplyDOMapper;
    }
}
