package cn.tpson.kulu.gas.dto;

import java.time.Instant;

/**
 * 设备定位信息.
 */
public class EqpLocationDTO extends BaseDTO {
    private Integer eqpId;

    private String location;

    public EqpLocationDTO() {}
    public EqpLocationDTO(Integer id, Boolean deleted, Instant gmtCreate, Instant gmtModified) {
        super(id, deleted, gmtCreate, gmtModified);
    }

    //////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////
    private String queryDay;

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

    public String getQueryDay() {
        return queryDay;
    }

    public void setQueryDay(String queryDay) {
        this.queryDay = queryDay;
    }
}