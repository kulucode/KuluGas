package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.constant.UserTypeEnum;
import cn.tpson.kulu.gas.domain.EqpDetectDO;
import cn.tpson.kulu.gas.dto.*;
import cn.tpson.kulu.gas.query.EqpDetectQuery;
import cn.tpson.kulu.gas.repository.BaseMapper;
import cn.tpson.kulu.gas.repository.EqpDetectDOMapper;
import cn.tpson.kulu.gas.service.EqpDetectLogService;
import cn.tpson.kulu.gas.service.EqpDetectService;
import cn.tpson.kulu.gas.service.EqpService;
import cn.tpson.kulu.gas.service.SysUserService;
import cn.tpson.kulu.gas.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
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
    @Autowired
    private SysUserService sysUserService;

    @Override
    public List<EqpDetectDTO> getByEqpId(Integer eqpId) {
        return BeanUtils.copyPropertiesForList(EqpDetectDTO.class, eqpDetectDOMapper.selectByEqpId(eqpId));
    }

    @Override
    public EqpDetectDTO getOneByEqpId(Integer eqpId) {
        return BeanUtils.copyProperties(EqpDetectDTO.class, eqpDetectDOMapper.selectOneByEqpId(eqpId));
    }

    @Override
    public Double getDetectValueByEqpId(Integer eqpId) {
        return eqpDetectDOMapper.selectDetectValueByEqpId(eqpId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(Integer eqpId, String detectValue, String detectNo, Short type, Double lat, Double lon) {
        Instant now = Instant.now();
        EqpDetectDTO eqpDetectDTO = new EqpDetectDTO(null, Boolean.FALSE, now, now);
        eqpDetectDTO.setEqpId(eqpId);
        eqpDetectDTO.setDetectValue(new BigDecimal(detectValue));
        eqpDetectDTO.setDetectNo(detectNo);
        eqpDetectDTO.setUid(getUser().getId());
        eqpDetectDTO.setStatus(EqpDetectDTO.STATUS_WAITING);
        eqpDetectDTO.setLat(lat);
        eqpDetectDTO.setLon(lon);
        eqpDetectDTO.setGmtDetect(now);

        // 判断进场检测还是日常检测
        List<EqpDetectDTO> rows = getByEqpId(eqpId);
        List<EqpDetectDTO> first = rows.stream().filter(dto -> dto.getType() == EqpDetectDTO.TYPE_FIRST).collect(Collectors.toList());
        List<EqpDetectDTO> usual = rows.stream().filter(dto -> dto.getType() == EqpDetectDTO.TYPE_USUAL).collect(Collectors.toList());
        eqpDetectDTO.setType(first.size() > 0 ? EqpDetectDTO.TYPE_USUAL : EqpDetectDTO.TYPE_FIRST);

        EqpDetectLogDTO eqpDetectLogDTO = BeanUtils.copyProperties(EqpDetectLogDTO.class, eqpDetectDTO);
        int logId = eqpDetectLogService.insert(eqpDetectLogDTO);
        eqpDetectDTO.setLogId(logId);

        int rst = 0;
        // 进场检测.
        if (Objects.equals(EqpDetectDTO.TYPE_FIRST, eqpDetectDTO.getType())) {
            rst = insert(eqpDetectDTO);

            //更新设备所属工地.
            SysUserDTO user = getUser();
            if (Objects.equals(UserTypeEnum.USER_BUI.getType(), user.getType())) {
                SysUserBuildingSiteDTO bs = sysUserService.getBuildingSiteByUid(user.getId());
                if (bs != null) {
                    EqpDTO update = new EqpDTO(eqpId, null, null, now);
                    update.setBsName(bs.getName());
                    update.setBsId(bs.getId());
                    eqpService.updateById(update);
                }
            }
            // 日常检测.
        } else if (Objects.equals(EqpDetectDTO.TYPE_USUAL, eqpDetectDTO.getType())) {
            if (usual.isEmpty()) {
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
                current.setStatus(EqpDetectDTO.STATUS_WAITING);
                current.setUid(eqpDetectDTO.getUid());

                updateById(current);
                rst = current.getId();
            }
        }

        // 生成区块链.
        if (rst > 0) {
            eqpDetectDTO = getById(rst);
            createBlockChain(eqpDetectDTO, SysBlockChainDTO.SOURCE_DETECT, getUser().getUsername(), eqpDetectDTO.getGmtModified(), rst);
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
        eqpDetectDOMapper.updateLogByPrimaryKey(BeanUtils.copyProperties(getGenericClassForDO(), record));
        return super.updateById(record);
    }

    @Override
    public void initEqp(EqpDetectDTO eqpDetectDTO) {
        if (eqpDetectDTO != null && eqpDetectDTO.getEqpId() != null) {
            eqpDetectDTO.setEqp(eqpService.getById(eqpDetectDTO.getEqpId()));
        }
    }

    @Override
    public void initUser(EqpDetectDTO eqpDetectDTO) {
        if (eqpDetectDTO != null && eqpDetectDTO.getUid() != null) {
            eqpDetectDTO.setUser(sysUserService.getById(eqpDetectDTO.getUid()));
        }
    }

    @Override
    public BaseMapper<EqpDetectQuery, EqpDetectDO> mapper() {
        return eqpDetectDOMapper;
    }
}
