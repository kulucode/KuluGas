package cn.tpson.kulu.gas.domain;

/**
 * 设备登录日志.
 */
public class EqpOnlineLogDO extends BaseDO {
    private Integer eqpId;

    private Double lat;

    private Double lon;

    private Boolean online;

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