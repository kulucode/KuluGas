package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.constant.EqpStatusEnum;
import cn.tpson.kulu.gas.domain.EqpApplyDO;
import cn.tpson.kulu.gas.dto.EqpApplyDTO;
import cn.tpson.kulu.gas.dto.EqpDTO;
import cn.tpson.kulu.gas.dto.SysBlockChainDTO;
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
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    public int apply(EqpApplyDTO eqpApplyDTO, Short status) {
        int id = super.insert(eqpApplyDTO);

        if (Objects.equals(EqpStatusEnum.STATUS_INSTALL_WAITING.getType(), status) || Objects.equals(EqpStatusEnum.STATUS_UNINSTALL_WAITING.getType(), status)) {
            final Map<String, Integer> params = new HashMap<>(2);
            eqpApplyDTO.getEqps().forEach(eqpDTO -> {
                if (Objects.equals(EqpStatusEnum.STATUS_INSTALL_WAITING.getType(), status)) {
                    eqpDTO.setUid(eqpApplyDTO.getUid());
                    eqpDTO.setGmtCreate(eqpApplyDTO.getGmtCreate());
                    eqpDTO.setDeleted(Boolean.FALSE);
                    eqpDTO.setStatus(status);
                    int eqpId = eqpService.insert(eqpDTO);

                    // 生成区块链
                    eqpService.createBlockChain(eqpDTO, SysBlockChainDTO.SOURCE_EQP, getUser().getUsername(), eqpDTO.getGmtCreate(), eqpId);
                } else {
                    eqpDTO.setStatus(status);
                    eqpService.updateById(eqpDTO);
                }

                params.put("applyId", id);
                params.put("eqpId", eqpDTO.getId());
                eqpApplyDOMapper.insertApplyEqp(params);
            });

        }


        return id;
    }

    @Override
    public BaseMapper<EqpApplyQuery, EqpApplyDO> mapper() {
        return eqpApplyDOMapper;
    }
}
