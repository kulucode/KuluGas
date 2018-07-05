package cn.tpson.kulu.gas.query;

/**
 * 设备定位信息.
 */
public class EqpLocationQuery extends BaseQuery {
    private Integer eqpId;

    private String location;

    //////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////
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