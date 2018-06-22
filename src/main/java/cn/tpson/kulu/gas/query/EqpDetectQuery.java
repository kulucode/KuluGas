package cn.tpson.kulu.gas.query;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

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

    /////////////////////////////////////////////////////////////////////////////
    private Integer uid;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String key;     // 搜索关键字
    private Short eqpType;  // 设备类型
    private Short source;   // 搜索表格来源

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
}