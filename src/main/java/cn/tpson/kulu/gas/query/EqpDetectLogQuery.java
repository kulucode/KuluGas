package cn.tpson.kulu.gas.query;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EqpDetectLogQuery extends BaseQuery {
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
     * 状态，0：未提交；1：已提交；
     */
    private Short status;

    /**
     * 检测类型，0：进场检测；1：定期检测；
     */
    private Short type;

    /**
     * 检测人.
     */
    private Integer uid;

    private Double lat;
    private Double lon;
    /////////////////////////////////////////////////////////////////////////////
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String key;     // 搜索关键字
    private Short eqpType;  // 设备类型

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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Short getEqpType() {
        return eqpType;
    }

    public void setEqpType(Short eqpType) {
        this.eqpType = eqpType;
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