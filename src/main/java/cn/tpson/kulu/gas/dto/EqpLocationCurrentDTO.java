package cn.tpson.kulu.gas.dto;

import java.time.Instant;

public class EqpLocationCurrentDTO extends BaseDTO {
    private Double lat;

    private Double lon;

    private Integer eqpId;

    private String area;

    private String addr;

    public EqpLocationCurrentDTO() {}
    public EqpLocationCurrentDTO(Integer id, Boolean deleted, Instant gmtCreate, Instant gmtModified) {
        super(id, deleted, gmtCreate, gmtModified);
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

    public Integer getEqpId() {
        return eqpId;
    }

    public void setEqpId(Integer eqpId) {
        this.eqpId = eqpId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}