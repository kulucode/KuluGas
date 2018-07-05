package cn.tpson.kulu.gas.query;

import cn.tpson.kulu.gas.dto.BaseDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class EqpDetectUrgeQuery extends BaseQuery {
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

    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    // 年份(年度处罚次数)
    private Short year;

    /**
     * 搜索关键字.
     */
    private String key;

    /**
     * 设备类型.
     */
    private Short eqpType;

    /**
     * 开始时间(用户时间查询).
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date st;

    /**
     * 截止时间(用户时间查询).
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date et;

    private Integer[] ids;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    public Short getEqpType() {
        return eqpType;
    }

    public void setEqpType(Short eqpType) {
        this.eqpType = eqpType;
    }

    public Date getSt() {
        return st;
    }

    public void setSt(Date st) {
        this.st = st;
    }

    public Date getEt() {
        return et;
    }

    public void setEt(Date et) {
        this.et = et;
    }
}