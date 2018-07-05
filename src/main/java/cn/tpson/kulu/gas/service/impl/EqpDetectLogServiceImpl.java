package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.domain.EqpDetectLogDO;
import cn.tpson.kulu.gas.dto.EqpDetectDTO;
import cn.tpson.kulu.gas.dto.EqpDetectLogDTO;
import cn.tpson.kulu.gas.dto.TableDTO;
import cn.tpson.kulu.gas.query.EqpDetectLogQuery;
import cn.tpson.kulu.gas.repository.BaseMapper;
import cn.tpson.kulu.gas.repository.EqpDetectLogDOMapper;
import cn.tpson.kulu.gas.service.EqpDetectLogService;
import cn.tpson.kulu.gas.service.EqpService;
import cn.tpson.kulu.gas.service.SysUserService;
import cn.tpson.kulu.gas.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Zhangka in 2018/06/15
 */
@Service
@Transactional(readOnly = true)
public class EqpDetectLogServiceImpl extends BaseServiceImpl<EqpDetectLogDTO, EqpDetectLogDO, EqpDetectLogQuery> implements EqpDetectLogService {
    @Autowired
    private EqpDetectLogDOMapper eqpDetectLogDOMapper;
    @Autowired
    private EqpService eqpService;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public List<EqpDetectLogDTO> getByEqpId(Integer eqpId) {
        return BeanUtils.copyPropertiesForList(EqpDetectLogDTO.class, eqpDetectLogDOMapper.selectByEqpId(eqpId));
    }

    @Override
    public EqpDetectLogDTO getOneByEqpId(Integer eqpId) {
        return BeanUtils.copyProperties(EqpDetectLogDTO.class, eqpDetectLogDOMapper.selectOneByEqpId(eqpId));
    }

    @Override
    public TableDTO<EqpDetectLogDTO> search(EqpDetectLogQuery query) {
        List<EqpDetectLogDTO> rows =  BeanUtils.copyPropertiesForList(EqpDetectLogDTO.class, eqpDetectLogDOMapper.search(query));
        Integer total = countBySearch(query);
        return new TableDTO<>(total, rows);
    }

    @Override
    public Integer countBySearch(EqpDetectLogQuery query) {
        return eqpDetectLogDOMapper.countBySearch(query);
    }

    @Override
    public void initEqp(EqpDetectLogDTO eqpDetectLogDTO) {
        if (eqpDetectLogDTO.getEqpId() != null) {
            eqpDetectLogDTO.setEqp(eqpService.getById(eqpDetectLogDTO.getEqpId()));
        }
    }

    @Override
    public void initUser(EqpDetectLogDTO eqpDetectLogDTO) {
        if (eqpDetectLogDTO.getEqpId() != null) {
            eqpDetectLogDTO.setUser(sysUserService.getById(eqpDetectLogDTO.getUid()));
        }
    }

    @Override
    public BaseMapper<EqpDetectLogQuery, EqpDetectLogDO> mapper() {
        return eqpDetectLogDOMapper;
    }
}
