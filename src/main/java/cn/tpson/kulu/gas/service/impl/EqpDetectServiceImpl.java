package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.domain.EqpDetectDO;
import cn.tpson.kulu.gas.dto.EqpDetectDTO;
import cn.tpson.kulu.gas.dto.EqpDetectLogDTO;
import cn.tpson.kulu.gas.dto.TableDTO;
import cn.tpson.kulu.gas.query.EqpDetectQuery;
import cn.tpson.kulu.gas.repository.BaseMapper;
import cn.tpson.kulu.gas.repository.EqpDetectDOMapper;
import cn.tpson.kulu.gas.service.EqpDetectLogService;
import cn.tpson.kulu.gas.service.EqpDetectService;
import cn.tpson.kulu.gas.service.EqpService;
import cn.tpson.kulu.gas.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zhangka in 2018/06/15
 */
@Service
@Transactional(readOnly = true)
public class EqpDetectServiceImpl extends BaseServiceImpl<EqpDetectDTO, EqpDetectDO, EqpDetectQuery> implements EqpDetectService {
    @Autowired
    private EqpDetectDOMapper eqpDetectDOMapper;
    @Autowired
    private EqpService eqpService;
    @Autowired
    private EqpDetectLogService eqpDetectLogService;

    @Override
    public List<EqpDetectDTO> getByEqpId(Integer eqpId) {
        return BeanUtils.copyPropertiesForList(EqpDetectDTO.class, eqpDetectDOMapper.selectByEqpId(eqpId));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(Integer eqpId, String detectValue, String detectNo, Short type) {
        Instant now = Instant.now();
        EqpDetectDTO eqpDetectDTO = new EqpDetectDTO(null, Boolean.FALSE, now, null);
        eqpDetectDTO.setEqpId(eqpId);
        eqpDetectDTO.setDetectValue(new BigDecimal(detectValue));
        eqpDetectDTO.setDetectNo(detectNo);
        eqpDetectDTO.setType(type);
        eqpDetectDTO.setStatus(EqpDetectDTO.STATUS_WAITING);

        List<EqpDetectDTO> rows = getByEqpId(eqpId);
        List<EqpDetectDTO> first = rows.stream().filter(dto -> dto.getType() == EqpDetectDTO.TYPE_FIRST).collect(Collectors.toList());
        List<EqpDetectDTO> usual = rows.stream().filter(dto -> dto.getType() == EqpDetectDTO.TYPE_USUAL).collect(Collectors.toList());
        eqpDetectDTO.setType(first.size() > 0 ? EqpDetectDTO.TYPE_USUAL : EqpDetectDTO.TYPE_FIRST);

        EqpDetectLogDTO eqpDetectLogDTO = BeanUtils.copyProperties(EqpDetectLogDTO.class, eqpDetectDTO);
        int logId = eqpDetectLogService.insert(eqpDetectLogDTO);

        int rst;
        if (eqpDetectDTO.getType() == EqpDetectDTO.TYPE_FIRST) {
            eqpDetectDTO.setLogId(logId);
            eqpDetectDTO.setGmtDetect(now);
            rst = insert(eqpDetectDTO);
        } else if (usual.size() == 0) {
            eqpDetectDTO.setLogId(logId);
            eqpDetectDTO.setGmtDetect(now);
            eqpDetectDTO.setGmtModified(now);
            rst = insert(eqpDetectDTO);
        } else {
            EqpDetectDTO current = usual.get(0);
            current.setLastLogId(current.getLogId());
            current.setLogId(logId);
            current.setGmtLastDetect(current.getGmtDetect());
            current.setGmtDetect(now);
            current.setGmtModified(now);
            current.setDetectValue(new BigDecimal(detectValue));
            current.setDetectNo(detectNo);
            eqpDetectDTO.setStatus(EqpDetectDTO.STATUS_WAITING);

            updateById(current);
            rst = current.getId();
        }

        return rst;
    }

    @Override
    public TableDTO<EqpDetectDTO> search(EqpDetectQuery query) {
        List<EqpDetectDTO> rows =  BeanUtils.copyPropertiesForList(EqpDetectDTO.class, eqpDetectDOMapper.search(query));
        Integer total = countBySearch(query);
        return new TableDTO<>(total, rows);
    }

    @Override
    public Integer countBySearch(EqpDetectQuery query) {
        return eqpDetectDOMapper.countBySearch(query);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateById(EqpDetectDTO record) {
        eqpDetectDOMapper.updateLogByPrimaryKey(BeanUtils.copyProperties(getGenericClassForDo(), record));
        return super.updateById(record);
    }

    @Override
    public void initEqp(EqpDetectDTO eqpDetectDTO) {
        if (eqpDetectDTO.getEqpId() != null) {
            eqpDetectDTO.setEqp(eqpService.getById(eqpDetectDTO.getEqpId()));
        }
    }

    @Override
    public BaseMapper<EqpDetectQuery, EqpDetectDO> mapper() {
        return eqpDetectDOMapper;
    }
}
