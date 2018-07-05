package cn.tpson.kulu.gas.schedule;

import cn.tpson.kulu.gas.dto.EqpDTO;
import cn.tpson.kulu.gas.dto.EqpDetectDTO;
import cn.tpson.kulu.gas.dto.EqpDetectUrgeDTO;
import cn.tpson.kulu.gas.dto.SysUserBuildingSiteDTO;
import cn.tpson.kulu.gas.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by Zhangka in 2018/07/04
 */
@Component
public class DetectUrgeSchedule {
    @Autowired
    private EqpService eqpService;
    @Autowired
    private EqpDetectService eqpDetectService;
    @Autowired
    private EqpOnlineLogService eqpOnlineLogService;
    @Autowired
    private EqpDetectUrgeService eqpDetectUrgeService;
    @Autowired
    private SysUserService sysUserService;

    @Scheduled(cron = "0 16 09 * * *")
    public void run() {
        List<Integer> ids = eqpService.getAllId();
        final Integer currentYear = currentYear();
        ids.forEach(id -> {
            EqpDetectDTO d = eqpDetectService.getOneByEqpId(id);
            if (d == null || d.getGmtDetect() == null) {
                EqpDetectUrgeDTO urge = eqpDetectUrgeService.getByYearAndEqpId(currentYear, id);
                Integer hours = (urge == null || urge.getGmtCheckin() == null)
                        ? eqpOnlineLogService.getWorkingHoursByEqpId(id)
                        : eqpOnlineLogService.getWorkingHoursByEqpIdAndGmtCreate(id, urge.getGmtCheckin().toInstant());

                if (hours != null && hours >= EqpDTO.EQP_DETECT_THRESHOLD) {
                    checkIn(id, urge, null, hours);
                }
            } else {
                Integer hours = eqpOnlineLogService.getWorkingHoursByEqpIdAndGmtCreate(id, d.getGmtDetect());
                if (hours == null || hours < EqpDTO.EQP_DETECT_THRESHOLD)
                    return;

                EqpDetectUrgeDTO urge = eqpDetectUrgeService.getByYearAndEqpId(currentYear, id);
                if (urge != null && urge.getGmtCheckin() != null) {
                    Integer lastCheckInHours = eqpOnlineLogService.getWorkingHoursByEqpIdAndGmtCreate(id, urge.getGmtCheckin().toInstant());
                    if (lastCheckInHours == null || lastCheckInHours < EqpDTO.EQP_DETECT_THRESHOLD)// 避免重复登记
                        return;
                }

                checkIn(id, urge, d.getGmtDetect(), hours);
            }
        });

        System.out.println("================================================================");
    }

    ///////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    protected void checkIn(Integer eqpId, EqpDetectUrgeDTO urge, Instant gmtDetect, Integer workingHours) {
        EqpDTO eqp = eqpService.getById(eqpId);
        if (eqp == null || eqp.getBsId() == null)
            return;

        SysUserBuildingSiteDTO bs = sysUserService.getBuildingSiteById(eqp.getBsId());
        if (bs == null)
            return;

        Instant now = Instant.now();
        if (urge == null) {
            urge = new EqpDetectUrgeDTO(null, Boolean.FALSE, now, now);
            urge.setEqpId(eqp.getId());
            urge.setEqpNo(eqp.getEqpNo());
            urge.setReason("超时未检");
            urge.setTimeoutHours(workingHours);
            urge.setNotDetectedYear(workingHours / EqpDTO.EQP_DETECT_THRESHOLD);
            urge.setBsId(eqp.getBsId());
            urge.setBsName(bs.getName());
            urge.setBsUsername(bs.getContactPerson());
            urge.setGmtDetect(gmtDetect == null ? null : Date.from(gmtDetect));
            urge.setGmtCheckin(Date.from(now));
            urge.setStatus(EqpDetectUrgeDTO.STATUS_UNSUBMIT);
            eqpDetectUrgeService.insert(urge);
        } else {
            EqpDetectUrgeDTO update = new EqpDetectUrgeDTO(urge.getId(), null, null, now);
            Integer notDetectedYear = urge.getNotDetectedYear() == null ? 0 : urge.getNotDetectedYear();
            update.setNotDetectedYear(notDetectedYear + (workingHours / EqpDTO.EQP_DETECT_THRESHOLD));
            update.setGmtCheckin(Date.from(now));
            update.setGmtDetect(gmtDetect == null ? null : Date.from(gmtDetect));
            eqpDetectUrgeService.updateById(update);
        }
    }


    protected Integer currentYear() {
        LocalDate now = LocalDate.now();
        return now.getYear();
    }
}
