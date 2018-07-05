package cn.tpson.kulu.gas.dto;

import java.time.Instant;

/**
 * 设备登录日志.
 */
public class EqpOnlineLogDTO extends BaseDTO {
    private Integer eqpId;

    private Double lat;

    private Double lon;

    private Boolean online;

    public EqpOnlineLogDTO() {}

    public EqpOnlineLogDTO(Integer id, Boolean deleted, Instant gmtCreate, Instant gmtModified) {
        super(id, deleted, gmtCreate, gmtModified);
    }

    public Integer getEqpId() {
        return eqpId;
    }

    public void setEqpId(Integer eqpId) {
        this.eqpId = eqpId;
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

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }
}