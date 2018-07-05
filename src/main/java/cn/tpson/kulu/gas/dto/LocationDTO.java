package cn.tpson.kulu.gas.dto;

/**
 * Created by Zhangka in 2018/06/15
 */
public class LocationDTO {
    private String addr;
    private Double lon;
    private Double lat;

    private String gmtLocation;

    public LocationDTO(Double lat, Double lon, String gmtLocation) {
        this.lat = lat;
        this.lon = lon;
        this.setGmtLocation(gmtLocation);
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getGmtLocation() {
        return gmtLocation;
    }

    public void setGmtLocation(String gmtLocation) {
        this.gmtLocation = gmtLocation;
    }
}
