package cn.tpson.kulu.gas.dto;

import cn.tpson.kulu.gas.json.InstantSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.time.Instant;

public class EqpPunishDTO extends BaseDTO {
    public static final short TYPE_1 = 1;
    public static final short TYPE_2 = 2;

    public static final short STATUS_0 = 0;
    public static final short STATUS_1 = 1;

    private String punishNo;

    private Integer bsId;

    private String phoneNo;

    private Integer uid;

    private String eqpNo;

    private Integer eqpId;

    private String detectNo;

    private Double lat;

    private Double lon;

    private BigDecimal amount;

    private String bsUsername;

    private String username;

    @JsonSerialize(using = InstantSerializer.class)
    private Instant gmtRepair;

    /**
     * 检测值.
     */
    private Double detectValue;

    /**
     * 处罚类型，1：漏报；2：超标；
     */
    private Short type;

    /**
     * 工地名称.
     */
    private String bsName;

    /**
     * 状态，0：未提交；1：已提交；
     */
    private Short status;

    public EqpPunishDTO() {}
    public EqpPunishDTO(Boolean deleted, Instant gmtCreate, Instant gmtModified) {
        super(null, deleted, gmtCreate, gmtModified);
    }

    public String getPunishNo() {
        return punishNo;
    }

    public void setPunishNo(String punishNo) {
        this.punishNo = punishNo;
    }

    public Integer getBsId() {
        return bsId;
    }

    public void setBsId(Integer bsId) {
        this.bsId = bsId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getEqpNo() {
        return eqpNo;
    }

    public void setEqpNo(String eqpNo) {
        this.eqpNo = eqpNo;
    }

    public String getDetectNo() {
        return detectNo;
    }

    public void setDetectNo(String detectNo) {
        this.detectNo = detectNo;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBsUsername() {
        return bsUsername;
    }

    public void setBsUsername(String bsUsername) {
        this.bsUsername = bsUsername;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Instant getGmtRepair() {
        return gmtRepair;
    }

    public void setGmtRepair(Instant gmtRepair) {
        this.gmtRepair = gmtRepair;
    }

    public Double getDetectValue() {
        return detectValue;
    }

    public void setDetectValue(Double detectValue) {
        this.detectValue = detectValue;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getBsName() {
        return bsName;
    }

    public void setBsName(String bsName) {
        this.bsName = bsName;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getEqpId() {
        return eqpId;
    }

    public void setEqpId(Integer eqpId) {
        this.eqpId = eqpId;
    }
}