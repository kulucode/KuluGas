package cn.tpson.kulu.gas.domain;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * 设备检查实体.
 */
public class EqpDetectDO extends BaseDO {
    /**
     * 设备id.--> t_eqp.
     */
    private Integer eqpId;

    /**
     * 检测结果.
     */
    private BigDecimal detectValue;

    /**
     * 检测设备编号.
     */
    private String detectNo;

    /**
     * 状态，0：未提交；1：提交服务公司；2：提交政府部门；
     */
    private Short status;

    /**
     * 检测类型，0：进场检测；1：定期检测；
     */
    private Short type;

    /**
     * 关联日志表id.
     */
    private Integer logId;

    /**
     * 关联日志表id.
     */
    private Integer lastLogId;

    /**
     * 检测时间.
     */
    private Instant gmtDetect;
    /**
     * 上次检测时间.
     */
    private Instant gmtLastDetect;

    /**
     * 检测人.

     */
    private Integer uid;

    private Double lat;
    private Double lon;

    public Integer getEqpId() {
        return eqpId;
    }

    public void setEqpId(Integer eqpId) {
        this.eqpId = eqpId;
    }

    public BigDecimal getDetectValue() {
        return detectValue;
    }

    public void setDetectValue(BigDecimal detectValue) {
        this.detectValue = detectValue;
    }

    public String getDetectNo() {
        return detectNo;
    }

    public void setDetectNo(String detectNo) {
        this.detectNo = detectNo;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getLastLogId() {
        return lastLogId;
    }

    public void setLastLogId(Integer lastLogId) {
        this.lastLogId = lastLogId;
    }

    public Instant getGmtDetect() {
        return gmtDetect;
    }

    public void setGmtDetect(Instant gmtDetect) {
        this.gmtDetect = gmtDetect;
    }

    public Instant getGmtLastDetect() {
        return gmtLastDetect;
    }

    public void setGmtLastDetect(Instant gmtLastDetect) {
        this.gmtLastDetect = gmtLastDetect;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}