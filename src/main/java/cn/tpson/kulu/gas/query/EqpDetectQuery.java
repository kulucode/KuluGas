package cn.tpson.kulu.gas.query;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public class EqpDetectQuery extends BaseQuery {
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
     * 检测类型，0：进场检测；1：定期检测；2：设备台账
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

    /////////////////////////////////////////////////////////////////////////////
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
    private String key;         // 搜索关键字
    private Short eqpType;      // 设备类型
    private Short eqpTypeKey;   // 设备类型
    private Short source;       // 搜索表格来源
    private String area;        // 所在区域
    private String disLevel;    // 排放等级

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

    public Short getSource() {
        return source;
    }

    public void setSource(Short source) {
        this.source = source;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDisLevel() {
        return disLevel;
    }

    public void setDisLevel(String disLevel) {
        this.disLevel = disLevel;
    }

    public Short getEqpTypeKey() {
        return eqpTypeKey;
    }

    public void setEqpTypeKey(Short eqpTypeKey) {
        this.eqpTypeKey = eqpTypeKey;
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