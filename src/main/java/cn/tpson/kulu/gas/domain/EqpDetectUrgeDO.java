package cn.tpson.kulu.gas.domain;

import java.util.Date;

public class EqpDetectUrgeDO extends BaseDO {
    private Integer eqpId;

    private String eqpNo;

    private String reason;

    private Integer timeoutHours;

    private Short notDetectedYear;

    private Integer bsId;

    private String bsName;

    private String bsUsername;

    private Date gmtDetect;

    private Date gmtCheckin;

    /**
     * 状态，0：未上报；1：已上报；
     */
    private Short status;

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

    public Short getNotDetectedYear() {
        return notDetectedYear;
    }

    public void setNotDetectedYear(Short notDetectedYear) {
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