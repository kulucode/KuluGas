package cn.tpson.kulu.gas.dto;

import java.time.Instant;
import java.util.Date;

public class EqpDetectUrgeDTO extends BaseDTO {
    /**
     * 状态，0：未上报；1：已上报；
     */
    public static final short STATUS_UNSUBMIT = 0;
    public static final short STATUS_SUBMITTED = 1;
    private Integer eqpId;

    private String eqpNo;

    private String reason;

    private Integer timeoutHours;

    private Integer notDetectedYear;

    private Integer bsId;

    private String bsName;

    private String bsUsername;

    private Date gmtDetect;

    private Date gmtCheckin;

    /**
     * 状态，0：未上报；1：已上报；
     */
    private Short status;

    public EqpDetectUrgeDTO() {}
    public EqpDetectUrgeDTO(Integer id, Boolean deleted, Instant gmtCreate, Instant gmtModified) {
        super(id, deleted, gmtCreate, gmtModified);
    }

    public Integer getEqpId() {
        return eqpId;
    }

    public void setEqpId(Integer eqpId) {
        this.eqpId = eqpId;
    }

    public String getEqpNo() {
        return eqpNo;
    }

    public void setEqpNo(String eqpNo) {
        this.eqpNo = eqpNo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getTimeoutHours() {
        return timeoutHours;
    }

    public void setTimeoutHours(Integer timeoutHours) {
        this.timeoutHours = timeoutHours;
    }

    public Integer getNotDetectedYear() {
        return notDetectedYear;
    }

    public void setNotDetectedYear(Integer notDetectedYear) {
        this.notDetectedYear = notDetectedYear;
    }

    public Integer getBsId() {
        return bsId;
    }

    public void setBsId(Integer bsId) {
        this.bsId = bsId;
    }

    public String getBsName() {
        return bsName;
    }

    public void setBsName(String bsName) {
        this.bsName = bsName;
    }

    public String getBsUsername() {
        return bsUsername;
    }

    public void setBsUsername(String bsUsername) {
        this.bsUsername = bsUsername;
    }

    public Date getGmtDetect() {
        return gmtDetect;
    }

    public void setGmtDetect(Date gmtDetect) {
        this.gmtDetect = gmtDetect;
    }

    public Date getGmtCheckin() {
        return gmtCheckin;
    }

    public void setGmtCheckin(Date gmtCheckin) {
        this.gmtCheckin = gmtCheckin;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}