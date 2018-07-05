package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.constant.ErrorCodeEnum;
import cn.tpson.kulu.gas.domain.EqpPunishDO;
import cn.tpson.kulu.gas.dto.EqpDTO;
import cn.tpson.kulu.gas.dto.EqpPunishDTO;
import cn.tpson.kulu.gas.dto.SysUserBuildingSiteDTO;
import cn.tpson.kulu.gas.dto.SysUserDTO;
import cn.tpson.kulu.gas.query.EqpPunishQuery;
import cn.tpson.kulu.gas.repository.BaseMapper;
import cn.tpson.kulu.gas.repository.EqpPunishDOMapper;
import cn.tpson.kulu.gas.service.EqpPunishService;
import cn.tpson.kulu.gas.service.EqpService;
import cn.tpson.kulu.gas.service.SysUserService;
import cn.tpson.kulu.gas.util.CheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Zhangka in 2018/07/02
 */
@Service
@Transactional(readOnly = true)
public class EqpPunishServiceImpl extends BaseServiceImpl<EqpPunishDTO, EqpPunishDO, EqpPunishQuery> implements EqpPunishService {
    @Autowired
    private EqpPunishDOMapper eqpPunishDOMapper;
    @Autowired
    private EqpService eqpService;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public BaseMapper<EqpPunishQuery, EqpPunishDO> mapper() {
        return eqpPunishDOMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String getPunishNumber() {
        LocalDateTime now = LocalDateTime.now();
        String dt = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return String.format(dt + "%06d", eqpPunishDOMapper.getPunishNumber());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public EqpPunishDTO punish(Integer eqpId, String amount, String detectNo, Date repairDate, Double lat, Double lon, Double detectValue, Short type, String bsName) {
        EqpDTO eqp = null;
        SysUserBuildingSiteDTO bs = null;
        if (eqpId != null) {
            eqp = eqpService.getById(eqpId);
            CheckUtils.checkNull(eqp, ErrorCodeEnum.EQP_NOT_FOUND);
            CheckUtils.checkNull(eqp.getBsId(), ErrorCodeEnum.EQP_BS_NOT_FOUND);
            bs = sysUserService.getBuildingSiteById(eqp.getBsId());
            CheckUtils.checkNull(bs, ErrorCodeEnum.EQP_BS_NOT_FOUND);
        }

        SysUserDTO currentUser = getUser();
        Instant now = Instant.now();
        EqpPunishDTO eqpPunishDTO = new EqpPunishDTO(Boolean.FALSE, now, now);
        eqpPunishDTO.setEqpNo(eqp != null ? eqp.getEqpNo() : null);
        eqpPunishDTO.setEqpId(eqpId);
        eqpPunishDTO.setDetectNo(detectNo);
        eqpPunishDTO.setAmount(new BigDecimal(amount));
        eqpPunishDTO.setLat(lat);
        eqpPunishDTO.setLon(lon);
        eqpPunishDTO.setPunishNo(getPunishNumber());
        eqpPunishDTO.setUid(currentUser.getId());
        eqpPunishDTO.setUsername(currentUser.getRealname());
        eqpPunishDTO.setGmtRepair(repairDate.toInstant());
        eqpPunishDTO.setDetectValue(detectValue);
        eqpPunishDTO.setType(type);
        eqpPunishDTO.setBsUsername(bs != null ? bs.getContactPerson() : null);
        eqpPunishDTO.setBsName(bs != null ? bs.getName() : null);
        eqpPunishDTO.setBsId(bs != null ? bs.getId() : null);
        eqpPunishDTO.setPhoneNo(bs != null ? bs.getPhoneNo() : null);

        insert(eqpPunishDTO);
        return eqpPunishDTO;
    }

    @Override
    public Integer getPunishCountByEqpNo(String eqpNo, Short year) {
        EqpPunishQuery query = new EqpPunishQuery();
        query.setEqpNo(eqpNo);
        query.setYear(year);
        return eqpPunishDOMapper.getPunishCountByEqpNo(query);
    }
}
