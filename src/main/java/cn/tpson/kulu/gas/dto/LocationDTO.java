package cn.tpson.kulu.gas.dto;

import java.math.BigDecimal;

/**
 * Created by Zhangka in 2018/06/15
 */
public class LocationDTO {
    private String addr;
    private BigDecimal lon;
    private BigDecimal lat;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }
}
