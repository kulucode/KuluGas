package cn.tpson.kulu.gas.domain;

/**
 * 设备定位信息.
 */
public class EqpLocationDO extends BaseDO {
    private Integer eqpId;

    private String location;

    public Integer getEqpId() {
        return eqpId;
    }

    public void setEqpId(Integer eqpId) {
        this.eqpId = eqpId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}