package cn.tpson.kulu.gas.service.impl;

import afu.org.checkerframework.checker.oigj.qual.O;
import cn.tpson.kulu.gas.constant.UserTypeEnum;
import cn.tpson.kulu.gas.domain.EqpDO;
import cn.tpson.kulu.gas.dto.*;
import cn.tpson.kulu.gas.query.EqpQuery;
import cn.tpson.kulu.gas.repository.BaseMapper;
import cn.tpson.kulu.gas.repository.EqpDOMapper;
import cn.tpson.kulu.gas.service.*;
import cn.tpson.kulu.gas.util.BeanUtils;
import cn.tpson.kulu.gas.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Zhangka in 2018/06/14
 */
@Service
@Transactional(readOnly = true)
public class EqpServiceImpl extends BaseServiceImpl<EqpDTO, EqpDO, EqpQuery> implements EqpService {
    @Autowired
    private EqpDOMapper eqpDOMapper;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private EqpDetectService eqpDetectService;
    @Autowired
    private EqpOnlineLogService eqpOnlineLogService;
    @Autowired
    private EqpPunishService eqpPunishService;

    @Override
    public EqpDTO getByEqpNo(String eqpNo) {
        return BeanUtils.copyProperties(EqpDTO.class, eqpDOMapper.selectByEqpNo(eqpNo));
    }

    @Override
    public EqpDTO detail(Integer eqpId) {
        EqpDTO eqpDTO = getById(eqpId);
        if (eqpDTO == null) {
            return null;
        }

        SysUserDTO user = sysUserService.getById(eqpDTO.getUid());
        if (Objects.equals(UserTypeEnum.USER_PER.getType(), user.getType())) {
            SysUserPersonalDTO detail = sysUserService.getPersonal(user.getId());
            if (detail != null) {
                eqpDTO.setIdCard(detail.getIdCard());
                eqpDTO.setPhoneNo(detail.getPhoneNo());
            }
        } else if (Objects.equals(UserTypeEnum.USER_BUI.getType(), user.getType())) {
            SysUserBuildingSiteDTO detail = sysUserService.getBuildingSiteByUid(user.getId());
            if (detail != null) {
                eqpDTO.setIdCard(detail.getLicenceNo());
                eqpDTO.setPhoneNo(detail.getPhoneNo());
            }
        }

        eqpDTO.setWorkingHours(eqpOnlineLogService.getWorkingHoursByEqpId(eqpId));
        eqpDTO.setPunishCount(eqpPunishService.getPunishCountByEqpNo(eqpDTO.getEqpNo(), (short)LocalDate.now().getYear()));
        eqpDTO.setUndetectCount(-1);
        if (eqpDTO.getBsId() != null) {
            eqpDTO.setBs(sysUserService.getBuildingSiteById(eqpDTO.getBsId()));
        }
        EqpDetectDTO eqpDetectDTO = eqpDetectService.getOneByEqpId(eqpId);
        if (eqpDetectDTO != null) {
            eqpDTO.setGmtDetect(eqpDetectDTO.getGmtDetect());
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime gmtDetect = LocalDateTime.ofInstant(eqpDetectDTO.getGmtDetect(), ZoneId.systemDefault());
            if (gmtDetect.getYear() != now.getYear()) {
                gmtDetect = gmtDetect.withYear(now.getYear());
            }
            Integer undetectCount = (now.getSecond() - gmtDetect.getSecond()) / (EqpDTO.EQP_DETECT_THRESHOLD * 3600);
            eqpDTO.setUndetectCount(undetectCount);
        }

        return eqpDTO;
    }

    @Override
    public List<Integer> getAllId() {
        return eqpDOMapper.getAllId();
    }

    @Override
    public void initUser(EqpDTO eqpDTO) {
        if (eqpDTO != null && eqpDTO.getUid() != null) {
            eqpDTO.setUser(sysUserService.getById(eqpDTO.getUid()));
        }
    }

    @Override
    public BaseMapper<EqpQuery, EqpDO> mapper() {
        return eqpDOMapper;
    }
}
